package ru.aplana.auto.steps;

import ru.aplana.auto.pages.SberSendPage;
import ru.yandex.qatools.allure.annotations.Step;

public class SberSendSteps {
    @Step("Кликаем на оформить Страховку")
    public void takeInsurance(){
        new SberSendPage().takeInsurance();
    }
}
