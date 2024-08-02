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
async function loadData(personaID, condition) {
  const btnFormCreate = document.getElementById('btn-form-create');
  btnFormCreate.style.display = 'none';
  const btnFormUpdate = document.getElementById('btn-form-update');
  btnFormUpdate.style.display = 'block';

  functionInputs(condition);

  const URLPersonas = `/personas/${personaID}`;
  const data = await dataPerson(URLPersonas);

  if (data.ok && data.data != 'Empty list') {
    document.getElementById('input-id').value = data.data.id;

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

async function updatePerson() {
  const nombre = document.getElementById('nombre').value;
  const apellido = document.getElementById('apellido').value;
  const celular = document.getElementById('celular').value;
  const genero = document.getElementById('genero').value;
  const tutor = document.getElementById('tutor').value;

  const pFormateo = document.getElementById('pFormateo').checked;
  const pLimpieza = document.getElementById('pLimpieza').checked;
  const pDesinfeccion = document.getElementById('pDesinfeccion').checked;
  const pDiagnosticar = document.getElementById('pDiagnosticar').checked;
  const pCambios = document.getElementById('pCambios').checked;

  const nFormateo = document.getElementById('nFormateo').checked;
  const nLimpieza = document.getElementById('nLimpieza').checked;
  const nDesinfeccion = document.getElementById('nDesinfeccion').checked;
  const nDiagnosticar = document.getElementById('nDiagnosticar').checked;
  const nCambios = document.getElementById('nCambios').checked;

  const cFlasheo = document.getElementById('cFlasheo').checked;
  const cBateria = document.getElementById('cBateria').checked;
  const cPantalla = document.getElementById('cPantalla').checked;
  const cVidrio = document.getElementById('cVidrio').checked;

  const aRecibir = document.getElementById('aRecibir').checked;
  const aPresupuestar = document.getElementById('aPresupuestar').checked;
  const aVenta = document.getElementById('aVenta').checked;
  const aCompra = document.getElementById('aCompra').checked;

  const body = {
    nombre,
    apellido,
    celular,
    genero,
    tutor,
    pformateo: pFormateo,
    plimpieza: pLimpieza,
    pdesinfeccion: pDesinfeccion,
    pdiagnosticar: pDiagnosticar,
    pcambios: pCambios,
    nformateo: nFormateo,
    nlimpieza: nLimpieza,
    ndesinfeccion: nDesinfeccion,
    ndiagnosticar: nDiagnosticar,
    ncambios: nCambios,
    cflasheo: cFlasheo,
    cbateria: cBateria,
    cpantalla: cPantalla,
    cvidrio: cVidrio,
    arecibir: aRecibir,
    apresupuestar: aPresupuestar,
    aventa: aVenta,
    acompra: aCompra
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
      const personaID = document.getElementById('input-id').value;
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

function functionInputs(condition) {
  document.getElementById('nombre').disabled = condition;
  document.getElementById('apellido').disabled = condition;
  document.getElementById('celular').disabled = condition;
  document.getElementById('genero').disabled = condition;
  document.getElementById('tutor').disabled = condition;

  document.getElementById('pFormateo').disabled = condition;
  document.getElementById('pLimpieza').disabled = condition;
  document.getElementById('pDesinfeccion').disabled = condition;
  document.getElementById('pDiagnosticar').disabled = condition;
  document.getElementById('pCambios').disabled = condition;

  document.getElementById('nFormateo').disabled = condition;
  document.getElementById('nLimpieza').disabled = condition;
  document.getElementById('nDesinfeccion').disabled = condition;
  document.getElementById('nDiagnosticar').disabled = condition;
  document.getElementById('nCambios').disabled = condition;

  document.getElementById('cFlasheo').disabled = condition;
  document.getElementById('cBateria').disabled = condition;
  document.getElementById('cPantalla').disabled = condition;
  document.getElementById('cVidrio').disabled = condition;

  document.getElementById('aRecibir').disabled = condition;
  document.getElementById('aPresupuestar').disabled = condition;
  document.getElementById('aVenta').disabled = condition;
  document.getElementById('aCompra').disabled = condition;
}

const btnFormUpdate = document.getElementById('btn-form-update');
btnFormUpdate.addEventListener('click', async (e) => {
  e.preventDefault();
  updatePerson();
});