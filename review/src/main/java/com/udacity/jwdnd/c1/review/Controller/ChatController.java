package com.udacity.jwdnd.c1.review.Controller;

import com.udacity.jwdnd.c1.review.Model.ChatForm;
import com.udacity.jwdnd.c1.review.Model.ChatMessage;
import com.udacity.jwdnd.c1.review.Service.MessageService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/chat")
public class ChatController {

    private MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String showMessages(ChatForm chatForm, Model model){
        System.out.println("In SHOW MESSAGES");
        System.out.println("Length of messageService List"+ this.messageService.getMessageList().size());
        model.addAttribute("messages", this.messageService.getMessageList());
        if(this.messageService.getMessageList().size() > 0){
            System.out.println("in SHOW MESSAGES text in first object" + this.messageService.getMessageList().get(0).getChatMessage());
        }


        return "chat";
    }

    @PostMapping
    public String updateMessages(Authentication authentication, ChatForm chatForm, Model model) {

        //String username;
        // Get the current logged in user
        //Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //if (principal instanceof UserDetails) {
        //    username = ((UserDetails)principal).getUsername();
        //} else {
        //    username = principal.toString();
        //}
        chatForm.setUserName(authentication.getName());

        this.messageService.addChatMessage(chatForm);
        //chatForm.setMessageText("");
        model.addAttribute("messages", this.messageService.getMessageList());
        return "chat";
    }

    @ModelAttribute("allMessageTypes")
    public String[] allMessageTypes(){
        return new String[] {"Say", "Shout", "Whisper"};
    }
}
