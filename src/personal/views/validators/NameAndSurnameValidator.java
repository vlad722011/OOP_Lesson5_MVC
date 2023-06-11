package personal.views.validators;

public class NameAndSurnameValidator {
    String userInput;

    public NameAndSurnameValidator(String userInput) {
        this.userInput = userInput;
    }
    public void validate () throws Exception {
        if(!(userInput.matches("^\\S+$"))){
            throw new Exception("Валидация не пройдена");
        }
    }
}
