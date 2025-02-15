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
    }

    @Test
    void homePageReturnString() {
        String result = homeController.homePage(model);
        assertEquals("HomePage", result);
        verify(model).addAttribute("title", "ADV Shop");
    }
}
