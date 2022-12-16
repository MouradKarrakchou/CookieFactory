package fr.unice.polytech.cod.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserComponent {

    @Autowired
    UserActionComponent userActionComponent;
    @Autowired
    UserRequestComponent userRequestComponent;

    public UserComponent(){}


}
