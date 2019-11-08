 function placespells()
{
 var ddl = document.getElementById("cardtype1");
 var selectedValue1 = ddl.options[ddl.selectedIndex].value;
    if (selectedValue1 == "selectcard")
   {
    App.snackbarCall("Please select spell 1.");
    return false;
   }

   var dd2 = document.getElementById("cardtype2");
 var selectedValue2 = dd2.options[dd2.selectedIndex].value;
    if (selectedValue2 == "selectcard")
   {
    App.snackbarCall("Please select spell 2.");
    return false;
   }

   var dd3 = document.getElementById("cardtype3");
 var selectedValue3 = dd3.options[dd3.selectedIndex].value;
    if (selectedValue3 == "selectcard")
   {
        App.snackbarCall("Please select spell 3.");
    return false;
   }


   var dd4 = document.getElementById("cardtype4");
 var selectedValue4 = dd4.options[dd4.selectedIndex].value;
    if (selectedValue4 == "selectcard")
   {
    App.snackbarCall("Please select spell 4.");
    return false;
   }


   var dd5 = document.getElementById("cardtype5");
 var selectedValue5 = dd5.options[dd5.selectedIndex].value;

    if (selectedValue5 == "selectcard")
   {
    App.snackbarCall("Please select spell 5.");
    return false;
   }


var array=[selectedValue1,selectedValue2,selectedValue3,selectedValue4,selectedValue5]
var result1=array.values();
for (let elements of result1) {
console.log(elements);
}
console.log(array);
App.placeSpells(array);

}