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

  const pFormateo = document.getElementById('pFormateo').value;
  const pLimpieza = document.getElementById('pLimpieza').value;
  const pDesinfeccion = document.getElementById('pDesinfeccion').value;
  const pDiagnosticar = document.getElementById('pDiagnosticar').value;
  const pCambios = document.getElementById('pCambios').value;

  const nFormateo = document.getElementById('nFormateo').value;
  const nLimpieza = document.getElementById('nLimpieza').value;
  const nDesinfeccion = document.getElementById('nDesinfeccion').value;
  const nDiagnosticar = document.getElementById('nDiagnosticar').value;
  const nCambios = document.getElementById('nCambios').value;

  const cFlasheo = document.getElementById('cFlasheo').value;
  const cBateria = document.getElementById('cBateria').value;
  const cPantalla = document.getElementById('cPantalla').value;
  const cVidrio = document.getElementById('cVidrio').value;

  const aRecibir = document.getElementById('aRecibir').value;
  const aPresupuestar = document.getElementById('aPresupuestar').value;
  const aVenta = document.getElementById('aVenta').value;
  const aCompra = document.getElementById('aCompra').value;

  const body = {
    nombre,
    apellido,
    celular,
    genero,
    tutor,
    pFormateo,
    pLimpieza,
    pDesinfeccion,
    pDiagnosticar,
    pCambios,
    nFormateo,
    nLimpieza,
    nDesinfeccion,
    nDiagnosticar,
    nCambios,
    cFlasheo,
    cBateria,
    cPantalla,
    cVidrio,
    aRecibir,
    aPresupuestar,
    aVenta,
    aCompra
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