const URLPersona = '/personas';

async function fetchPacientes(_url, _token = null, _method = 'GET', _body = null) {
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

async function searchPaciente() {
  const busqueda = document.getElementById('nombreapellido').value;
  const data = await fetchPacientes(URLPersona + `/search/${busqueda}`);
  loadHTML(data);
}

async function loadPacientes() {
  const data = await fetchPacientes(URLPersona);
  loadHTML(data);
}

async function deletePaciente(id) {
  Swal.fire({
    title: "¿Está seguro?",
    text: "No podrá recuperar este registro.",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Sí, elimínalo!",

  }).then(async (result) => {
    if (result.isConfirmed) {

      const data = await fetchPacientes(URLPersona + `/${id}`, null, 'DELETE', null);

      if (data.ok) {
        Swal.fire({
          title: "Eliminado!",
          text: "El registro ha sido eliminado.",
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
    loadPacientes();
  } else {
    searchPaciente();
  }
});

window.onload = async () => {
  loadPacientes();
}

function loadHTML(data) {
  const ulList = document.getElementById('ul-list-person');
  ulList.innerHTML = '';

  if (data.ok && data.data != "Empty list") {
    for (paciente of data.data) {
      const { nombre, apellido, id } = paciente;

      const html_Li = `<a href="list.html">
                          <i class="uil uil-user"></i>
                          <span class="person-span-name">${apellido} ${nombre}</span>
                        </a>
                        <span class="person-span-options">
                          <a href=""><i class="uil uil-edit a-success" id="${id}"></i></a>
                          <a href="#"><i class="uil uil-trash-alt a-danger" id="${id}"></i></a>
                        </span>`
      const li = document.createElement('li');
      li.innerHTML = html_Li;
      ulList.appendChild(li);
    }
  }
}
