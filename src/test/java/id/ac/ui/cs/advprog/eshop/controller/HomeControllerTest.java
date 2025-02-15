package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

    @InjectMocks
    HomeController homeController;

    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        // This method is currently empty because there are no setup steps required for these tests.
        // If initialization is needed in the future, it should be added here.
    }

    @Test
    void homePageReturnString() {
        String result = homeController.homePage(model);
        assertEquals("HomePage", result);
        verify(model).addAttribute("title", "ADV Shop");
    }
}
