const API = "https://updatecheck-99fb.onrender.com/api/urls";

async function loadUrls(){
    const res = await fetch(API);
    const urls = await res.json();

    const list = document.getElementById("urlList");
    list.innerHTML="";

    urls.forEach(u=>{
        const div=document.createElement("div");
        div.innerText=u.url;
        list.appendChild(div);
    });
}

async function addUrl(){
    const input=document.getElementById("urlInput");

    await fetch(API,{
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify({
            url:input.value
        })
    });

    input.value="";
    loadUrls();
}