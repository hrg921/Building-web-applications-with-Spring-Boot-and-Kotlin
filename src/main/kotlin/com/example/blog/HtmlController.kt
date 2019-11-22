package com.example.blog

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

// Notice that we are using here a Kotlin extension
// that allows to add Kotlin functions or operators to existing Spring types.
// Here we import the org.springframework.ui.set extension function
// in order to be able to write model["title"] = "Blog"
// instead of model.addAttribute("title", "Blog").
// The Spring Framework KDoc API lists all the Kotlin extensions provided to enrich the Java API.

@Controller
class HtmlController {
    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = "Blog"
        return "blog"
    }
}
