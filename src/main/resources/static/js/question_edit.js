window.onload = function()
{
    var trueVariants = document.getElementsByName("btn-delete");
    for(let i=0; i<trueVariants.length; i++)
    {
        trueVariants[i].addEventListener('click', function(){this.parentNode.remove()})
    }
}

function addVariant()
{
    var div  = document.createElement('div');
    var inputVar = document.createElement('input');
    inputVar.name = "inputAnswer";
    div.appendChild(inputVar);
    var buttonDelete = document.createElement('button');
    buttonDelete.type = "button";
    buttonDelete.innerHTML = "Удалить вариант";
    buttonDelete.addEventListener('click', function(){this.parentNode.remove()})
    div.appendChild(buttonDelete);
    var trueText = document.createElement('a');
    trueText.innerHTML = "верный ответ";
    document.getElementById('answers-form').insertBefore(div, document.getElementById('btn-add'));
}





