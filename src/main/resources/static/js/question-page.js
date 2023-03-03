function submitFormType3(event)
{
    const variants = document.getElementsByName("checkBoxAnswer");
    let ok = false;
    let i = 0;
    while(i < variants.length)
    {
        if(variants[i].checked)
        {
            ok = true;
        }
        i++;
    }
    if(ok == false)
    {
        event.preventDefault();
        alert("не выбраны верные варианты ответа");
    }
}

function submitFormType2(event)
{
    const inputAnswer = document.getElementById("inputAnswer");
    if(String(inputAnswer.value).replace(/^ +| +$|( ) +/g, "$1") == "")
    {
        event.preventDefault();
        alert("ответ пуст или не написан");
    }
}