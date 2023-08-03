const url = "http://localhost:8081/api/users/"


async function getAdminPage() {
    let page = await fetch(url);

    if(page.ok) {
        let listAllUser = await page.json();
        loadTableData(listAllUser);
    } else {
        alert(`Error, ${page.status}`)
    }
}

window.addEventListener('DOMContentLoaded', getAdminPage);
function loadTableData(listAllUser) {
    const tableBody = document.getElementById('tbody');
    let dataHtml = '';
    for (let user of listAllUser) {
        let roles = [];
        for (let role of user.roles) {
            roles.push(" " + role.simpleName)
        }
        dataHtml +=
            `<tr>
                <td>${user.id}</td>
                <td>${user.lastName}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${roles}</td>
                <td>
                    <button class="btn btn-primary" data-bs-toogle="modal"
                    data-bs-target="#editModal"
                    onclick="editModalData(${user.id})">Изменить</button>
                </td>
                    <td>
                    <button class="btn btn-danger" data-bs-toogle="modal"
                    data-bs-target="#deleteModal"
                    onclick="deleteModalData(${user.id})">Удалить</button>
                </td>
            </tr>`
    }
    tableBody.innerHTML = dataHtml;
}
getAdminPage();

const form_new = document.getElementById('formForNewUser');

async function newUser() {
    form_new.addEventListener('submit', addNewUser);
}

async function addNewUser(event) {
    event.preventDefault();
    let roleList = [];
    for (let i = 0; i < form_new.roles.options.length; i++) {
        if (form_new.roles.options[i].selected) {
            let setRole = {};
            roleList.push(setRole = {id: roles.options[i].value});
        }
    }
    let method = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: form_new.name.value,
            lastName: form_new.lastName.value,
            age: form_new.age.value,
            email: form_new.email.value,
            password: form_new.password.value,
            roles: roleList
        })
    }
    let create = await fetch(url, method);
    if (create.status === 400) {
        alert(JSON.parse(await create.text()).message)
    } else {
        document.getElementById('nav-users_table-tab').click()
        form_new.reset();
        await getAdminPage();
    }

}
const form_ed = document.getElementById('formForEditing');
const id_ed = document.getElementById('id_ed');
const name_ed = document.getElementById('name_ed');
const lastName_ed = document.getElementById('lastName_ed');
const age_ed = document.getElementById('age_ed');
const email_ed = document.getElementById('email_ed');
const password_ed = document.getElementById('password_ed');


async function editModalData(id) {
    $('#editModal').modal('show');
    let usersPageEd = await fetch(url + id);
    if (usersPageEd.ok) {
        await usersPageEd.json().then(user => {
            id_ed.value = `${user.id}`;
            name_ed.value = `${user.name}`;
            lastName_ed.value = `${user.lastName}`;
            age_ed.value = `${user.age}`;
            email_ed.value = `${user.email}`;
            password_ed.value = `${user.password}`;

        })
    } else {
        alert(`Error, ${usersPageEd.status}`)
    }
}
async function editUser() {
    let roleList = [];
    for (let i = 0; i < form_ed.rolesForEditing.options.length; i++) {
        if (form_ed.rolesForEditing.options[i].selected) {
            let setRole = {};
            roleList.push(setRole = {id: rolesForEditing.options[i].value});
        }
    }
    let method = {
        method: 'PATCH',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            id: form_ed.oldUserId.value,
            name: form_ed.name.value,
            lastName: form_ed.lastName.value,
            age: form_ed.age.value,
            email: form_ed.email.value,
            password: form_ed.password.value,
            roles: roleList
        })
    }
    let edit = await fetch(url, method);
    if (edit.status === 400) {
        alert(JSON.parse(await edit.text()).message)
    } else {
        $('#editCloseBtn').click();
        await getAdminPage();
    }
}

const id_del = document.getElementById('id_del');
const name_del = document.getElementById('name_del');
const lastName_del = document.getElementById('lastName_del');
const age_del = document.getElementById('age_del');
const email_del = document.getElementById('email_del');
const password_del = document.getElementById('password_del');


async function deleteModalData(id) {
    $('#deleteModal').modal('show');
    let usersPageDel = await fetch(url + id);
    if (usersPageDel.ok) {
        await usersPageDel.json().then(user => {
            id_del.value = `${user.id}`;
            name_del.value = `${user.name}`;
            lastName_del.value = `${user.lastName}`;
            age_del.value = `${user.age}`;
            email_del.value = `${user.email}`;
            password_del.value = ``;
        })
    } else {
        alert(`Error, ${usersPageDel.status}`)
    }
}
async function deleteUser() {
    await fetch(url + id_del.value, {method: 'DELETE'})
        .then(() => {
        $('#deleteCloseBtn').click();
        getAdminPage();
    })
}