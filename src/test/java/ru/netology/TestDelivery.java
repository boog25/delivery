package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class TestDelivery {

String getDate(int shift) {

    return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

}

    String meetingDate = getDate(4);
    @Test
    void shouldNewDelivery() {
        //Configuration.headless=true;
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue("Уфа").click();
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").sendKeys(meetingDate);
        $("[name='name']").setValue("Иванов Павел");
        $("[name='phone']").setValue("+79122593411");
        $("[class = 'checkbox__box']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована на")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(withText(meetingDate)).shouldBe(Condition.visible, Duration.ofSeconds(15));

    }
}
