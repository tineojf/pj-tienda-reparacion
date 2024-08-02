const URLPersona = '/personas';

async function fetchPerson(_url, _token = null, _method = 'GET', _body = null) {
  const options = {
    method: _method,
    headers: { "Content-Type": "application/json" }
  };
  if (_token) { options.headers["Authorization"] = "Bearer " + _token; }
  if (_body) { options.body = JSON.stringify(_body); }

  try {
    const response = await fetch(_url, options);
    const data = await response.json();
    return data;
  } catch (error) {
    console.log(error);
  }
}

async function searchPerson() {
  const busqueda = document.getElementById('nombreapellido').value;
  const data = await fetchPerson(URLPersona + `/search/${busqueda}`);
  loadHTML(data);
}

async function loadPerson() {
  const data = await fetchPerson(URLPersona);
  loadHTML(data);
}

async function deletePerson(id) {
  Swal.fire({
    title: "¿Está seguro?",
    text: "No podrá recuperar este trabajador.",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Sí, elimínalo!",

  }).then(async (result) => {
    if (result.isConfirmed) {

      const data = await fetchPerson(URLPersona + `/${id}`, null, 'DELETE', null);

      if (data.ok) {
        Swal.fire({
          title: "Eliminado!",
          text: "El trabajador ha sido eliminado.",
          icon: "success"
        }).then((result) => {
          window.location.reload();
        });

      } else {
        Swal.fire({
          icon: 'error',
          title: 'Error...',
          text: data ? data.message : 'Unknown error'
        });
      }
    }
  });
}

const inputSearch = document.getElementById('nombreapellido');
inputSearch.addEventListener('keyup', async (e) => {
  if (inputSearch.value.length == 0) {
    loadPerson();
  } else {
    searchPerson();
  }
});

window.onload = async () => {
  const btnFormUpdate = document.getElementById('btn-form-update');
  btnFormUpdate.style.display = 'none';
  loadPerson();
}

function loadHTML(data) {
  const ulList = document.getElementById('ul-list-person');
  ulList.innerHTML = '';

  if (data.ok && data.data != "Empty list") {
    for (paciente of data.data) {
      const { nombre, apellido, id } = paciente;

      const html_Li = `<a href="list.html">
                          <i class="uil uil-user"></i>
                          <a href="#" class="person-span-name" onclick="loadData(${id})">${apellido} ${nombre}</a>
                        </a>
                        <span class="person-span-options">
                          <a href="#" onclick="loadData(${id})"><i class="uil uil-edit a-success" id="${id}"></i></a>
                          <a href="#" onclick="deletePerson(${id})"><i class="uil uil-trash-alt a-danger" id="${id}"></i></a>
                        </span>`
      const li = document.createElement('li');
      li.innerHTML = html_Li;
      ulList.appendChild(li);
    }
  }
}
