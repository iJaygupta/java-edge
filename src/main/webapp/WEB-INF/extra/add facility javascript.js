$("#addFacilityForm").submit(function(e) {
        $("#addFacilitySubmitButton").attr("disabled", "disabled");
        var form = $(this);
        var url = "addFacility";

        $.ajax({
               type: "POST",
               url: url,
               data: form.serialize(), // serializes the form's elements.
               success: function(data)
               {
                   var val = data.trim();
                   if(val==="Facility Not Added"){
                       setAlertMessage("alert-warning","Ops! ",val);
                   }else{
                       addFacilityInList(val);
                       setAlertMessage("alert-success","Seccessfully! ","New Facility Added");
                       
                   }
                   handleFormModel("Facility");
                   removeAlertMessage();
               }
             });

        e.preventDefault(); // avoid to execute the actual submit of the form.
    });
    
    function addFacilityInList(val){
        var facilityList = document.getElementById("facilityList");
        var facility = JSON.parse(val);
        console.log(facility);
        var divRow = document.createElement("div");
        divRow.setAttribute("class","row");
        
        var divCol4 = document.createElement("div");
        divCol4.setAttribute("class","col-lg-4");
        
        var divPanel = document.createElement("div");
        divPanel.setAttribute("class","panel panel-"+facility.colorCode);
        
        var divPanelHeading = document.createElement("div");
        divPanelHeading.setAttribute("class","panel-heading");
        
//        var heading = document.createTextNode(${c+1}+". "+facility.name);
        
        var divPanelBody = document.createElement("div");
        divPanelBody.setAttribute("class","panel-body");
        
        var divPanelFooter = document.createElement("div");
        divPanelFooter.setAttribute("class","panel-footer");
        
        var br = document.createElement("br");
        var bodyText1 = document.createTextNode("Contact: "+facility.contact);
        var bodyText2 = document.createTextNode("Address "+facility.address);
        var bodyText = document.createElement("p");
        bodyText.appendChild(bodyText1);
        bodyText.appendChild(br);
        bodyText.appendChild(bodyText2);
        
        var footerText = document.createTextNode("Facility Code: "+facility.facilityCode);
        
        divPanelFooter.appendChild(footerText);
        divPanelBody.appendChild(bodyText);
        divPanelHeading.appendChild(heading);
        divPanel.appendChild(divPanelHeading);
        divPanel.appendChild(divPanelBody);
        divPanel.appendChild(divPanelFooter);
        divCol4.appendChild(divPanel);
        divRow.appendChild(divCol4);
        facilityList.append(divRow);
    }