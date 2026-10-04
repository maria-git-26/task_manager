document.addEventListener('DOMContentLoaded', () => {
    // Filter Buttons Toggle
    const filterBtns = document.querySelectorAll('.filter-btn');
    filterBtns.forEach(btn => {
        btn.addEventListener('click', () => {
            filterBtns.forEach(b => b.classList.remove('active'));
            btn.classList.add('active');
        });
    });

    // Theme Toggle Elements

    document.querySelectorAll(".theme-switch span").forEach(button => {
        button.addEventListener("click", () => {
            document
                .querySelectorAll(".theme-switch span")
                .forEach(item => item.classList.remove("selected"));

            button.classList.add("selected");
        });
    });



    const toggleD = document.getElementById('dark-theme-toggle');
    const toggleL = document.getElementById('light-theme-toggle');

    const appC = document.querySelector('.app-container');
    const bd = document.body;
    const don = document.querySelector('.donut');
    const mod = document.querySelector('.modal');

    toggleD.addEventListener('click', () => {
        appC.classList.toggle('dark-theme');
        bd.classList.toggle('dark-theme');
        don.classList.toggle('dark-theme');
        mod.classList.toggle('dark-theme');
    });

    toggleL.addEventListener('click', () => {
        appC.classList.remove('dark-theme');
        bd.classList.remove('dark-theme');
        don.classList.remove('dark-theme');
        mod.classList.remove('dark-theme');
    });



    const checkboxes = document.querySelectorAll('.checkbox-item input');
    checkboxes.forEach(box => {
        box.addEventListener('change', (e) => {
            const text = e.target.parentElement.querySelector('.text');
            if (e.target.checked) {
                text.style.textDecoration = 'line-through';
                text.style.opacity = '0.6';
            } else {
                text.style.textDecoration = 'none';
                text.style.opacity = '1';
            }
        });
    });

    window.onload = () => {
        loadTasks();
    };

    async function loadTasks() {

        const token =
            localStorage.getItem("token");

        const response = await fetch(
            "/tasks/getTasks",
            {
                headers: {
                    Authorization:
                        "Bearer " + token
                }
            }
        );

        const tasks =
            await response.json();

        renderTasks(tasks);
    }

    function renderTasks(tasks) {
        console.log((tasks));
        const table = document.getElementById('tableList');


        tasks.forEach(task => {


            const row = table.insertRow();
            row.insertCell().innerHTML = '<input type="checkbox">';
            row.insertCell().textContent = task.title;
            row.insertCell().textContent = task.description;
            row.insertCell().textContent = task.priority;
            row.insertCell().textContent = task.status;


        });
    }

});

async function toMainMenu() {
    window.location.href =
        "/main_menu/index.html";
}

const modal = document.getElementById("modal");
const openBtn = document.getElementById("openModal");
const closeBtn = document.getElementById("closeModal");
const cancelBtn = document.getElementById("cancelBtn");

openBtn.addEventListener("click", () => {
    modal.classList.add("active");
});

closeBtn.addEventListener("click", () => {
    modal.classList.remove("active");
});

cancelBtn.addEventListener("click", () => {
    modal.classList.remove("active");
});

modal.addEventListener("click", (e) => {
    if (e.target === modal) {
        modal.classList.remove("active");
    }
});

document
    .getElementById("taskForm")
    .addEventListener("submit", async (e) => {
        e.preventDefault();

        const task = {
            title: document.getElementById("title").value,
            description: document.getElementById("description").value,
            project: document.getElementById("project").value,
            assignee: document.getElementById("assignee").value,
            deadline: document.getElementById("deadline").value,
            priority: document.querySelector(
                'input[name="priority"]:checked'
            ).value
        };
        const token =
            localStorage.getItem("token");
        const response = await fetch(

            "http://localhost:8080/tasks",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization:
                        "Bearer " + token
                },
                body: JSON.stringify({
                    title: task.title,
                    description: task.description,
                    priority: task.priority,
                    status: "in motion"
                })
            }
        );

        console.log(task);

        alert("Задача создана!");

        modal.classList.remove("active");
        e.target.reset();
    });

document.addEventListener('DOMContentLoaded', () => {
    const cards = document.querySelectorAll('.card');
    const columnBodies = document.querySelectorAll('.column-body');
    const columns = document.querySelectorAll('.column');

    // Функция обновления счетчика задач в колонках
    function updateCounts() {
        columns.forEach(column => {
            const count = column.querySelectorAll('.card').length;
            column.querySelector('.task-count').textContent = `${count} tasks`;
        });
    }

    // Настройка карточек для перетаскивания
    cards.forEach(card => {
        // Начало перетаскивания
        card.addEventListener('dragstart', () => {
            card.classList.add('dragging');
        });

        // Конец перетаскивания
        card.addEventListener('dragend', () => {
            card.classList.remove('dragging');
            updateCounts(); // Обновляем счетчики после завершения
        });
    });

    // Настройка колонок для приема карточек
    columnBodies.forEach(body => {
        // Разрешаем сброс (drop)
        body.addEventListener('dragover', (e) => {
            e.preventDefault(); // Необходимо для разрешения drop
            // Добавляем визуальный эффект на колонку
            body.closest('.column').classList.add('drag-over');
        });

        // Убираем визуальный эффект при выходе
        body.addEventListener('dragleave', (e) => {
            body.closest('.column').classList.remove('drag-over');
        });

        // Обработка сброса карточки
        body.addEventListener('drop', (e) => {
            e.preventDefault();
            body.closest('.column').classList.remove('drag-over');

            // Находим перетаскиваемую карточку
            const draggingCard = document.querySelector('.card.dragging');

            if (draggingCard) {
                // Перемещаем карточку в новую колонку
                body.appendChild(draggingCard);
            }
        });
    });

    // Инициализация счетчиков при первой загрузке
    updateCounts();
});