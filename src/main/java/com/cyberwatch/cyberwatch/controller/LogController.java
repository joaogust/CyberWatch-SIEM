package com.cyberwatch.cyberwatch.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/*
Essa anotação é necessária porque a aplicação irá tratar com uma
API REST (Representation State Transfer).
A API REST é um conjunto de regras e padrões que possibilitam
dois ou mais sistemas de software se comunicarem pela internet
funcionando como uma ponte
 */
@RequestMapping("/logs")
public class LogController {

}
