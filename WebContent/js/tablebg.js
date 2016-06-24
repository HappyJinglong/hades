function changeTableBg(tableId)
{
var changeTr=document.getElementById(tableId).getElementsByTagName("tr");
   for(i=0;i<changeTr.length;i++){
      changeTr[i].className=(i%2>0)?"even":"odd";
   }
}