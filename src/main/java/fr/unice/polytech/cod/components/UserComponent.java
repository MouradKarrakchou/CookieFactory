package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.interfaces.UserAction;
import fr.unice.polytech.cod.interfaces.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserComponent {

    @Autowired
    UserAction UserAction;
    @Autowired
    UserRequest UserRequest;

    public UserComponent(){}


}
