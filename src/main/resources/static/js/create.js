async function dataPersonas(_url, _token = null, _method = 'GET', _body = null) {

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

async function createPaciente() {
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

  const URLPersonas = '/personas';
  const data = await dataPersonas(URLPersonas, null, 'POST', body);

  if (data.ok) {
    Swal.fire({
      icon: 'success',
      title: '¡Operación exitosa!',
      text: 'Paciente creado exitosamente'
    }).then((result) => {
      window.location.href = '../index.html';
    });
  } else {
    Swal.fire({
      icon: 'error',
      title: 'Error...',
      text: data ? data.message : 'Unknown error'
    });
  }
}

const formcreate = document.getElementById('form-post-paciente');
formcreate.addEventListener('submit', async (e) => {
  e.preventDefault();
  createPaciente();
});