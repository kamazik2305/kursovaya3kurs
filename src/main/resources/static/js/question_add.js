
//создание форм для ответов для добавления вопроса
function alertValue(value)
{
    deleteForms();
    var div = createAnswersForm();
    var button = document.createElement('button');
    button.type = "button";
    button.id = "btn-confirm";
    button.innerHTML = "подтвердить варианты";
    div.appendChild(button);
    switch(parseInt(value))
    {
        case 0: deleteForms();
            break;
        case 1: button.onclick = function(){confirmQuestionType1();}
            break;
        case 2: button.type = "submit";
                button.innerHTML = "Добавить вопрос";
            break;
        case 3: button.onclick = function(){confirmQuestionType3();}
            break;
    }
}

function createAnswersForm()
{
    var div = document.createElement('div');
    div.id = "answers-form";
    for(let i = 0; i < 4; i++)
    {
        addVariant(div);
    }
    var buttonAdd = document.createElement('button');
    buttonAdd.id = "btn-add";
    buttonAdd.type = "button";
    buttonAdd.innerHTML = "добавить вариант";
    buttonAdd.onclick = function(){addVariant(div);}
    div.appendChild(buttonAdd);
    var formAdd = document.getElementById('form-add');
    formAdd.appendChild(div);
    return div;
}

function confirmQuestionType1()
{
    var div = document.createElement('div');
    div.id = "true-answers-form";

    const variants = document.getElementsByName("inputAnswer");
    for(let i=0; i<variants.length; i++)
    {
        if(String(variants[i].value).replace(/^ +| +$|( ) +/g, "$1") != "")
        {
        
        var label = document.createElement('label');
        label.innerHTML = variants[i].value;

        var inputVar = document.createElement('input');
        inputVar.type = "checkbox";
        inputVar.value = variants[i].value;
        inputVar.name = "buttonTrueAnswer";
        inputVar.autocomplete="off"
        inputVar.addEventListener('change', function() {
            var trueVariants = document.getElementsByName("buttonTrueAnswer");
            for(let i=0; i<trueVariants.length; i++)
                trueVariants[i].checked = false;
            this.checked = true;
        })
        div.appendChild(label);
        div.appendChild(inputVar);
        }
    }
    createButtons(div);
}

function confirmQuestionType3()
{
    var div = document.createElement('div');
    div.id = "true-answers-form";
    const variants = document.getElementsByName("inputAnswer");
    for(let i=0; i<variants.length; i++)
    {
        if(String(variants[i].value).replace(/^ +| +$|( ) +/g, "$1") != "")
        {
        var divVar = document.createElement('div');
        var inputTrueVar = document.createElement('input');
        inputTrueVar.type = "checkbox";
        inputTrueVar.name = "inputTrueAnswer";
        inputTrueVar.autocomplete="off"
        inputTrueVar.value = variants[i].value;
        divVar.appendChild(inputTrueVar);
        var label = document.createElement('label');
        label.innerHTML = variants[i].value;
        divVar.appendChild(label);
        div.appendChild(divVar);
        } 
    }
    createButtons(div);
}

function deleteForms()
{
    var answersForm = document.getElementById('answers-form');
    var trueAnswersForm = document.getElementById('true-answers-form');
    if(answersForm)
        answersForm.parentNode.removeChild(answersForm);
    if(trueAnswersForm)
        trueAnswersForm.parentNode.removeChild(trueAnswersForm);
}

function addVariant(parentElem)
{
    var div = document.createElement('div');

    var inputVar = document.createElement('input');
    inputVar.name = "inputAnswer";
    inputVar.placeholder = "введите вариант";
    inputVar.autocomplete="off";
    var buttonDelete = document.createElement('button');
    buttonDelete.type = "button";
    buttonDelete.innerHTML = "Удалить вариант";
    buttonDelete.addEventListener('click', function(){this.parentNode.remove()})
    div.appendChild(inputVar);
    div.appendChild(buttonDelete);
    var btn = document.getElementById('btn-add');
    parentElem.insertBefore(div, btn);
}

function createButtons(div)
{
    var buttonChange = document.createElement('button');
    buttonChange.type = "button";
    buttonChange.innerHTML = "Поменять варианты";
    buttonChange.onclick = function() {changeVariants();}
    div.appendChild(buttonChange);
    var buttonAdd = document.createElement('button');
    buttonAdd.type ="submit";
    buttonAdd.innerHTML = "Добавить вопрос";
    div.appendChild(buttonAdd);
    var formAdd = document.getElementById('form-add');
    formAdd.appendChild(div);
    var answersForm = document.getElementById('answers-form');
    answersForm.hidden = true;
}

function changeVariants()
{
    var trueAnswersForm = document.getElementById('true-answers-form');
    trueAnswersForm.parentNode.removeChild(trueAnswersForm);
    var answersForm = document.getElementById('answers-form');
    answersForm.hidden = false;
}

function submitForm(event)
{
    if(String(document.getElementById('txt').value).replace(/^ +| +$|( ) +/g, "$1") == "")
    {
        event.preventDefault();
        alert("еблан введи текст");
    }
    var typeQuestion = document.getElementById('selectType');
    switch(parseInt(typeQuestion.value))
    {
        case 1:const trueButtons = document.getElementsByName("buttonTrueAnswer");
        if(trueButtons.length < 2)
        {
            event.preventDefault();
            alert("недостаточно вариантов ответа");
        }
            break;
        case 3:const trueVariants = document.getElementsByName("inputTrueAnswer");
        if(trueVariants.length < 2)
        {
            event.preventDefault();
            alert("недостаточно вариантов ответа");
        }
        let ok = false;
        let i = 0;
        while(i < trueVariants.length){
            if(trueVariants[i].checked)
            {
                ok = true;
            }
            i++;
        }
        if(ok == false){
            event.preventDefault();
            alert("не выбраны верные варианты ответа");
        }
            break;
    }   
}