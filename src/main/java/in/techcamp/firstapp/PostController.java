package in.techcamp.firstapp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;

    @GetMapping("/hello")
    public String showHello(Model model){
        var sampleText = (21.1 + 20.5) / 2;
        model.addAttribute("sampleText", sampleText);
        return "hello";
    }

    @GetMapping
    public ModelAndView showList(ModelAndView mav) {
        var postList = postRepository.findAll();
        mav.setViewName("index");
        mav.addObject("postList", postList);
        return mav;
    }

    @GetMapping("/postForm")
    public ModelAndView showPostForm(@ModelAttribute("postForm") PostForm form, ModelAndView mav) {
        mav.setViewName("postForm");
        return mav;
    }

    @PostMapping("/posts")
    public ModelAndView savePost(PostForm form, ModelAndView mav) {
        postRepository.insert(form.getMemo());
        return new ModelAndView("redirect:/");
    }
}
