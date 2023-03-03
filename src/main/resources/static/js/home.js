function submitSearchForm(event)
{
    const inputAnswer = document.getElementById("searchString");
    if(String(inputAnswer.value).replace(/^ +| +$|( ) +/g, "$1") == "")
    {
        event.preventDefault();
        alert("запрос пуст или не написан");
    }
}