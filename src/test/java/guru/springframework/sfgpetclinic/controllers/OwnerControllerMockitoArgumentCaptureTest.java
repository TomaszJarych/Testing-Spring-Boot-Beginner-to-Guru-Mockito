package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class OwnerControllerMockitoArgumentCaptureTest {
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
    }

    @Test
    void processFindFromWildcardString() {
        //given
        Owner owner = new Owner(1L, "Joe", "Buck");
        List<Owner> stubList = new ArrayList<>();
        final ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        given(ownerService.findAllByLastNameLike(argumentCaptor.capture())).willReturn(stubList);

        //when
        String viewName = controller.processFindForm(owner, bindingResult, null);

        //then

        assertThat("%Buck%").isEqualToIgnoringCase(argumentCaptor.getValue());
    }

    @Test
    void processFindFromWildcardStringArgumentCaptorAnnotation() {
        //given
        Owner owner = new Owner(1L, "Joe", "Buck");
        List<Owner> stubList = new ArrayList<>();

        given(ownerService.findAllByLastNameLike(annotatedArgumentCaptor.capture())).willReturn(stubList);

        //when
        String viewName = controller.processFindForm(owner, bindingResult, null);

        //then

        assertThat("%Buck%").isEqualToIgnoringCase(annotatedArgumentCaptor.getValue());
    }
}