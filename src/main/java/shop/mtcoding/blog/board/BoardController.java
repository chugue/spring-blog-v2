package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding._core.errors.exception.Exception403;
import shop.mtcoding._core.errors.exception.Exception404;
import shop.mtcoding.blog.user.User;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardRepository boardRepository;
    private final HttpSession session;

    @PostMapping("/board/{id}/delete")
    public String deleteById(@PathVariable(name = "id") Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardRepository.findById(id);

        if (sessionUser.getId() != board.getUser().getId()) {
            throw new Exception403("게시글을 삭제할 권한이 없습니다");
        }

        boardRepository.deleteById(id);
        return "redirect:/";
    }


    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardRepository.save(reqDTO.toEntity(sessionUser));
        return "redirect:/";
    }

    @PostMapping("/board/{id}/update")
    public String updateById(@PathVariable(name = "id") Integer id, BoardRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardRepository.findById(id);

        if (sessionUser.getId() != board.getUser().getId()) {
            throw new Exception403("게시글을 수정할 권한이 없습니다");
        }

        boardRepository.updateById(id, reqDTO.getTitle(), reqDTO.getContent());
        return "redirect:/board/" + id;
    }

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardRepository.findAll();
        request.setAttribute("boardList", boardList);
        boardList.forEach(board -> {
            System.out.println(board.getUser().getUsername());
        });

        return "index";
    }


    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }


    @GetMapping("/board/{id}")
    public String detail(@PathVariable(name = "id") Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardRepository.findByIdJoinUser(id);
        // 기본 false 유지
        // 로그인 자체를 안했음 false
        // 로그인 햇는데 게시글 주인이 아니면 false
        // 로그인 했는데 게시글 주인이라면 true
        boolean isOwner = false;
        if (sessionUser != null) {
            if (sessionUser.getId() == board.getUser().getId()) {
                isOwner = true;
            }
        }
        request.setAttribute("board", board);
        request.setAttribute("isOwner", isOwner);
        return "board/detail";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable(name = "id") Integer id, HttpServletRequest request) {
        Board board = boardRepository.findById(id);
        if (board == null) {
            throw new Exception404("해당 게시글을 찾을 수 없습니다");
        }


        request.setAttribute("board", board);
        return "board/update-form";
    }
}
