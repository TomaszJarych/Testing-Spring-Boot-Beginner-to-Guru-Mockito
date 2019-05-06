package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class OwnerControllerMockitoAnswers {
    private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";

    @Mock
    private OwnerService ownerService;

    @Mock
    BindingResult bindingResult;

    private OwnerController controller;

    @Captor
    ArgumentCaptor<String> annotatedArgumentCaptor;

    @BeforeEach
    void setUp() {
        controller = new OwnerController(ownerService);
        given(ownerService.findAllByLastNameLike(annotatedArgumentCaptor.capture())).willAnswer(invocation -> {
            List<Owner> stubList = new ArrayList<>();
            String name = invocation.getArgument(0);
            if (name.equals("%Buck%")) {
                stubList.add(new Owner(1L, "Joe", "Buck"));
                return stubList;
            } else if (name.equals("%DontFindMe%")) {
                return stubList;
            } else if (name.equals("%FindMe%")) {
                stubList.add(new Owner(1L, "Joe", "FindMe"));
                stubList.add(new Owner(2L, "John", "FindMe"));
                return stubList;
            } else {
                throw new RuntimeException("Invalid argument");
            }
        });
    }


    @Test
    void processFindFromWildcardStringArgumentCaptorAnnotation() {
        //given
        Owner owner = new Owner(1L, "Joe", "Buck");

        //when
        String viewName = controller.processFindForm(owner, bindingResult, null);

        //then

        assertThat("%Buck%").isEqualToIgnoringCase(annotatedArgumentCaptor.getValue());
        assertThat("redirect:/owners/1").isEqualToIgnoringCase(viewName);
    }

    @Test
    void processFindFromWildcardFindMultipleOwners() {
        //given
        Owner owner = new Owner(1L, "Joe", "FindMe");

        //when
        String viewName = controller.processFindForm(owner, bindingResult, null);

        //then

        assertThat("%FindMe%").isEqualToIgnoringCase(annotatedArgumentCaptor.getValue());
        assertThat("owners/ownersList").isEqualToIgnoringCase(viewName);
    }

    @Test
    void processFindFromWildcardNotFound() {
        //given
        Owner owner = new Owner(1L, "Joe", "DontFindMe");

        //when
        String viewName = controller.processFindForm(owner, bindingResult, Mockito.mock(Model.class));

        //then

        assertThat("%DontFindMe%").isEqualToIgnoringCase(annotatedArgumentCaptor.getValue());
        assertThat("owners/findOwners").isEqualToIgnoringCase(viewName);
    }
}