<html><head><title>Dynamic view</title>
        <#include "DivCss.css">
  <body>
    <form name="ListOfInfo" action="hello" method="POST">
    <div class="WholeTable">

<#list Fields as field>  
    <div id="div_lib_${field.name}" class="DescDiv">${field.desc}&nbsp;&nbsp;&nbsp;</div>

        <div id="div_${field.name}" class="InpDiv">
                <#if field.formType == "TextBox">
                <input type="text" name="${field.name}"/>
                <#elseif 
                field.formType == "CheckBox">
                <input type="checkbox" name="${field.name}"/>
                <#elseif
                field.formType == "date">
                <input type="date" name="${field.name}"/>
                <#elseif
                 field.formType == "Select">
                <select name="${field.name}">
                     <option value="male">male</option>
                     <option value="female">female</option>
                    </select>
                <#elseif
                 field.formType == "Submit">
                <input type="submit" name="${field.name}"/>
                </#if></br></br>
              
        </div>
</#list>
        
    </div>
      </form>
  </body>
</html>
