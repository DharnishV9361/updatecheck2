const API =  "https://updatecheck-99fb.onrender.com/api/urls";

async function loadUrls(){

const res = await fetch(API);

const urls = await res.json();

const list = document.getElementById("urlList");

list.innerHTML="";

urls.forEach(u=>{

const div=document.createElement("div");

div.className="card";

div.innerHTML=`
<b>${u.url}</b>
<p>Status: ${u.active ? "Active":"Inactive"}</p>
`;

list.appendChild(div);

});

}

async function addUrl(){

const url=document.getElementById("urlInput").value;

await fetch(API+"/urls",{

method:"POST",

headers:{
"Content-Type":"application/json"
},

body:JSON.stringify({
url:url,
userId:1
})

});

loadUrls();

}

loadUrls();