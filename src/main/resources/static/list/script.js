const searchInput = document.getElementById("search");
const rows = document.querySelectorAll("#taskTable tr");

searchInput.addEventListener("keyup", () => {
    const value = searchInput.value.toLowerCase();

    rows.forEach(row => {
        const text = row.textContent.toLowerCase();

        row.style.display =
            text.includes(value)
                ? ""
                : "none";
    });
});