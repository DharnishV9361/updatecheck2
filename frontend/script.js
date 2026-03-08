const API = "https://updatecheck-99fb.onrender.com/api/urls";

async function addUrl(){

const url = document.getElementById("urlInput").value;

await fetch(`${API}/urls`,{
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

async function loadUrls(){

const res = await fetch(`${API}/urls`);
const data = await res.json();

document.getElementById("siteCount").innerText = data.length;

const list = document.getElementById("urlList");
list.innerHTML="";

data.forEach(u=>{

list.innerHTML += `
<div class="flex justify-between bg-gray-100 p-3 rounded-lg">

<span>
${u.url}
</span>

<span class="text-green-600 font-semibold">
🟢 Active
</span>

</div>
`;

});

}

async function loadChanges(){

const res = await fetch(`${API}/changes`);
const data = await res.json();

document.getElementById("changeCount").innerText = data.length;

const list = document.getElementById("changeLog");
list.innerHTML="";

data.forEach(c=>{

list.innerHTML += `
<div class="bg-red-100 text-red-700 p-3 rounded-lg">

🔴 Change detected (URL ID: ${c.trackedUrlId})

</div>
`;

});

}

loadUrls();
loadChanges();

/* AUTO REFRESH */

setInterval(()=>{

loadUrls();
loadChanges();

},5000);