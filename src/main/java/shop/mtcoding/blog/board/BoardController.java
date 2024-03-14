package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardRepository boardRepository;

    @PostMapping("/board/{id}/update")
    public String updateById (@PathVariable(name = "id") Integer id){

        return "redirect:/board/"+id;
    }

    @PostMapping("board/save")
    public String save (){
        return "redirect:/";
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
        Board board = boardRepository.findByIdJoinUser(id);
        request.setAttribute("board", board);
        return "board/detail";
    }

    @PostMapping("/board/{id}/delete")
    public String delete (@PathVariable Integer id){
        return "redirect:/";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable(name ="id") Integer id) {
        return "board/update-form";
    }


}
