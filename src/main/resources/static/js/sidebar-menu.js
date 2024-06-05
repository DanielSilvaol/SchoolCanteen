// sidebar-menu.js

document.addEventListener("DOMContentLoaded", function() {
    // Fetch sidebar menu content
    fetch('/school/html/sidebar-menu.html')
        .then(response => response.text())
        .then(html => {
            // Insert sidebar menu content into the page
            document.querySelector('#sidebar-menu').innerHTML = html;
        });
});
