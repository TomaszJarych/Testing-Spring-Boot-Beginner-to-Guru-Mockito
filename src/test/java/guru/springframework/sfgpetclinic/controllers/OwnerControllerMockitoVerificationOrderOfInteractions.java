package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerMockitoVerificationOrderOfInteractions {
    private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";

    @Mock
    private OwnerService ownerService;

    @Mock
    BindingResult bindingResult;

    @Mock
    Model model;

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
                throw new RuntimeException("Invalid argument has been passed");
            }
        });
    }


    @Test
    @DisplayName("Order verification")
    void orderVerification() {
        //given
        Owner owner = new Owner(1L, "Joe", "FindMe");
        InOrder order = inOrder(ownerService, model);

        //when
        String viewName = controller.processFindForm(owner, bindingResult, model);

        //then

        assertThat("%FindMe%").isEqualToIgnoringCase(annotatedArgumentCaptor.getValue());
        assertThat("owners/ownersList").isEqualToIgnoringCase(viewName);

        //inOrderAssertions
        order.verify(ownerService).findAllByLastNameLike(anyString());
        order.verify(model).addAttribute(anyString(), anyList());

        //verify no more interactions with model
        verifyNoMoreInteractions(model);
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

        //verify no interactions with model
        verifyZeroInteractions(model);
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
        //verify no interactions with model
        verifyZeroInteractions(model);
    }
}