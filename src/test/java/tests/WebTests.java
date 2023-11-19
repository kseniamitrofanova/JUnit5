package tests;

import data.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class WebTests {

    @BeforeEach
    void setUp()
    {
        open("https://www.startpage.com/");
    }

    @ValueSource(strings = {
            "Selenide", "JUnit 5", "Allure"
    })
    @ParameterizedTest(name="For found {0} on startpage.com")
    @Tag("BLOCKER")
    void searchResultsShouldNotBeEmpty(String searchQuery){
        $("#q").setValue(searchQuery).pressEnter();
        $$("[class='w-gl__result__main']")
                .shouldBe(sizeGreaterThan(0));
    }

    @CsvSource(value = {
            "Selenide, https://selenide.org/",
            "JUnit 5, https://junit.org"
    })
    @ParameterizedTest(name="For found {0} in first card {1}")
    @Tag("BLOCKER")
    void searchResultsShouldContainExpectedUrl(String searchQuery, String expectedLink){
        $("#q").setValue(searchQuery).pressEnter();
        $("[class='w-gl__result-url result-link']")
                .shouldHave(text(expectedLink));
    }

    @Tag("BLOCKER2")
    @EnumSource(Language.class)
    @ParameterizedTest
        //@DisplayName("For found junit 5")
    void successfulSearchWithEnum(Language language){
        $("#q").setValue(language.description).pressEnter();
        $$("[class='w-gl__result__main']")
                .shouldBe(sizeGreaterThan(0));
    }

    @Tag("BLOCKER")
    @EnumSource(Language.class)
    @ParameterizedTest
    void successfulSearchTwo(Language language){
        $("#q").setValue(language.description).pressEnter();
        $("#q").shouldHave(text(language.description));
    }

}
