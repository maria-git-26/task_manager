const tasks = [
{
name:"Изучить Spring Security",
status:"work",
priority:"Высокий",
category:"Backend",
deadline:"12.09.2026",
created:"05.09.2026"
},
{
name:"Настроить Docker",
status:"todo",
priority:"Средний",
category:"DevOps",
deadline:"15.09.2026",
created:"07.09.2026"
},
{
name:"Разработать UI календаря",
status:"todo",
priority:"Низкий",
category:"Frontend",
deadline:"18.09.2026",
created:"08.09.2026"
}
];

const tbody = document.getElementById("tasks");

tasks.forEach(task => {

tbody.innerHTML += 
<tr>

<td>
<input type="checkbox"></input>
</td>

<td>${task.name}</td>

<td>
<span class="badge ${task.status}">
${task.status}
</span>
</td>

<td>${task.priority}</td>
<td>${task.category}</td>
<td>${task.deadline}</td>
<td>${task.created}</td>

</tr>
;

});

const darkBtn =
document.getElementById("darkBtn");

const lightBtn =
document.getElementById("lightBtn");

darkBtn.onclick = () => {

document.documentElement
.setAttribute("data-theme","dark");

darkBtn.classList.add("active");
lightBtn.classList.remove("active");

localStorage.setItem("theme","dark");
};

lightBtn.onclick = () => {

document.documentElement
.setAttribute("data-theme","light");

lightBtn.classList.add("active");
darkBtn.classList.remove("active");

localStorage.setItem("theme","light");
};

const savedTheme =
localStorage.getItem("theme");

if(savedTheme){

document.documentElement
.setAttribute("data-theme",savedTheme);

if(savedTheme==="dark"){

darkBtn.classList.add("active");
lightBtn.classList.remove("active");

}
}