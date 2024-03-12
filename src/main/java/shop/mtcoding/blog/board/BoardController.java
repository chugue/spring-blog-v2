package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardNativeRepository boardNativeRepository;

    @PostMapping("board/save")
    public String save (String title, String content, String username){
        boardNativeRepository.save(title, content, username);
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(HttpServletRequest request) {

        List<Board> boardList = boardNativeRepository.findAll();
        request.setAttribute("boardList",boardList);
        return "index";
    }


    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id, HttpServletRequest request) {
        Board board = boardNativeRepository.findById(id);
        request.setAttribute("board", board);

        return "board/detail";
    }

    @PostMapping("/board/{id}/delete")
    public String delete (@PathVariable Integer id){
        boardNativeRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable(name ="id") Integer id, HttpServletRequest request) {
        Board board = boardNativeRepository.findById(id);
        request.setAttribute("board", board);

        return "board/detail";
    }
}
