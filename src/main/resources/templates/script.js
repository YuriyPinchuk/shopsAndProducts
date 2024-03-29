// download date from server on load page
window.onload = function () {
    ajaxGet("http://localhost:8080/")
};
//function for get date from server and create page by this date
function ajaxGet(url) {
    let request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState === 4) {
            let responseString = request.response; // get the string from the response
            let responsObject = JSON.parse(responseString); // convert it to an object
            let shopsList = "<ul>"; //create list of shops
            let shopsSelect = "<select name='shopName' id='shopId'>"; //create shops select
            for (let i = 0; i < responsObject.length; i++) {
                shopsList += "<li onclick='showProducts(event)'>" + responsObject[i].shopName;
                shopsSelect += "<option>" + responsObject[i].shopName + "</option>";
                // if shop have some products, add list with them to this shop
                // if not just close tag
                if (responsObject[i].products.length > 0) {
                    shopsList += "<ul style='display: none'>";
                    for (let j = 0; j < responsObject[i].products.length; j++) {
                        shopsList += "<li>" + responsObject[i].products[j].productName + "</li>";
                    }
                    shopsList += "</ul>" + "</li>";
                } else {
                    shopsList += "</li>"
                }
            }
            shopsList += "</ul>";
            shopsSelect += "</select>";
            document.querySelector('#shopsContainer').innerHTML = shopsList;
            document.querySelector('#shopSelect').innerHTML = shopsSelect;
        }
    };
    request.open('GET', url);
    request.send();
}

function ajaxPost(params, url) {
    let request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState === 4) {
            ajaxGet("http://localhost:8080/");
        }
    };
    request.open('POST', url);
    request.send(params);
}

function saveShop() {
    ajaxPost(document.getElementById("shopName").value, 'http://localhost:8080/saveShop');
}

function saveProduct() {
    let productValue = document.getElementById("productName").value;
    let shopIdValue = document.getElementById("shopId").value;
    let params = "productName=" + productValue + "&" + "shopId=" + shopIdValue;
    ajaxPost(params, 'http://localhost:8080/saveProduct');
}

function showProducts(e) {
    console.log(e.target);
    if (e.target.childNodes[1].style.display === 'none') {
        e.target.childNodes[1].style.display = 'block'
    } else {
        e.target.childNodes[1].style.display = 'none';
    }
}
