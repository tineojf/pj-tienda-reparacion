async function dataPerson(_url, _token = null, _method = 'GET', _body = null) {
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
async function loadData(personaID) {
  const btnFormCreate = document.getElementById('btn-form-create');
  btnFormCreate.style.display = 'none';
  const btnFormUpdate = document.getElementById('btn-form-update');
  btnFormUpdate.style.display = 'block';

  const URLPersonas = `/personas/${personaID}`;
  const data = await dataPerson(URLPersonas);

  if (data.ok && data.data != 'Empty list') {
    document.getElementById('nombre').value = data.data.nombre;
    document.getElementById('apellido').value = data.data.apellido;
    document.getElementById('celular').value = data.data.celular;
    document.getElementById('genero').value = data.data.genero;
    document.getElementById('tutor').value = data.data.tutor;

    document.getElementById('pFormateo').checked = data.data.pformateo;
    document.getElementById('pLimpieza').checked = data.data.plimpieza;
    document.getElementById('pDesinfeccion').checked = data.data.pdesinfeccion;
    document.getElementById('pDiagnosticar').checked = data.data.pdiagnosticar;
    document.getElementById('pCambios').checked = data.data.pcambios;

    document.getElementById('nFormateo').checked = data.data.nformateo;
    document.getElementById('nLimpieza').checked = data.data.nlimpieza;
    document.getElementById('nDesinfeccion').checked = data.data.ndesinfeccion;
    document.getElementById('nDiagnosticar').checked = data.data.ndiagnosticar;
    document.getElementById('nCambios').checked = data.data.ncambios;

    document.getElementById('cFlasheo').checked = data.data.cflasheo;
    document.getElementById('cBateria').checked = data.data.cbateria;
    document.getElementById('cPantalla').checked = data.data.cpantalla;
    document.getElementById('cVidrio').checked = data.data.cvidrio;

    document.getElementById('aRecibir').checked = data.data.arecibir;
    document.getElementById('aPresupuestar').checked = data.data.apresupuestar;
    document.getElementById('aVenta').checked = data.data.aventa;
    document.getElementById('aCompra').checked = data.data.acompra;
  } else {
    Swal.fire({
      icon: 'error',
      title: 'Error...',
      text: data ? data.message : 'Unknown error'
    })
  }
}

async function updatePerson2() {
  const personaID = window.location.search.split('=')[1];
  const nombre = document.getElementById('nombre').value;
  const apellido = document.getElementById('apellido').value;
  const dni = document.getElementById('dni').value;
  const fechaIngreso = document.getElementById('ingreso').value;
  const domicilio = {
    calle: document.getElementById('calle').value,
    numero: document.getElementById('numero').value,
    localidad: document.getElementById('localidad').value,
    provincia: document.getElementById('provincia').value
  };

  const body = {
    nombre,
    apellido,
    dni,
    fechaIngreso,
    domicilio
  };

  Swal.fire({
    title: "¿Quiere actualizar la información?",
    showDenyButton: true,
    showCancelButton: true,
    confirmButtonText: "Actualizar",
    denyButtonText: `No actualizar`,
    cancelButtonText: `Cancelar`,

  }).then(async (result) => {
    if (result.isConfirmed) {
      const URLPerson = `/personas/${personaID}`;
      const data = await dataPersonas(URLPerson, null, 'PUT', body);

      if (data.ok) {
        Swal.fire("Actualizado!", "", "success").then(() => {
          window.location.href = '../index.html';
        });

      } else {
        Swal.fire({
          icon: 'error',
          title: 'Error...',
          text: data ? data.message : 'Unknown error'
        })
      }

    } else if (result.isDenied) {
      Swal.fire("La información no se actualizó", "", "info").then(() => {
        window.location.href = '../index.html';
      });
    }
  });
}

const formupdate = document.getElementById('form-update-paciente');
formupdate.addEventListener('submit', async (e) => {
  e.preventDefault();
  updatePerson();
});