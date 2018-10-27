package io.exnihilo.validator.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.Yaml;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.Map;

@RestController
public class ValidatorController {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    
    private ApiInfo apiInfo() {
        return new ApiInfo(
          "This REST API", 
          "Validates yaml, json and xml files. Hi!!",
          "API TOS", 
          "Terms of service", 
          new Contact("Anand Varkey Philips", "about.me/anandvarkeyphilips", "anandvarkey.philips@gmail.com"),
          "License of API", "API license URL", Collections.emptyList());
   }


    @PostMapping("/yaml")
    public String validateYAML(@RequestBody String yamlData) {

        Yaml yaml = new Yaml();
        try {
            Map<String, Object> obj = yaml.load(yamlData);
            System.out.println(obj);
            return "Validated";
        } catch (ClassCastException e) {
            return "Invalid YAML!";
        } catch (Exception e) {
            return "Error occured in validation";
        }
    }
}
