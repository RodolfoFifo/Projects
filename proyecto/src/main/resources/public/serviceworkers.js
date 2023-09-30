//Cache activo.
var CACHE_NAME = 'badday10';
//listado de
var urlsToCache = [
    '/icons/font-awesome/fonts/fontawesome-webfont3e6e.woff2?v=4.7.0',
    '/icons/font-awesome/fonts/fontawesome-webfont3e6e.woff?v=4.7.0',
    '/icons/font-awesome/fonts/fontawesome-webfont3e6e.ttf?v=4.7.0',
    '/icons/simple-line-icons/fonts/Simple-Line-Icons4c82.ttf?-i3a2kk',
    '/icons/simple-line-icons/fonts/Simple-Line-Icons4c82.woff2?-i3a2kk',
    '/icons/simple-line-icons/fonts/Simple-Line-Icons4c82.woff?-i3a2kk',
    '/images/tv.png',
    '/plugins/tables/css/datatable/dataTables.bootstrap4.min.css',
    '/images/playdom.png',
    '/images/user/form-user.png',
    '/plugins/validation/jquery.validate.min.js',
    '/plugins/validation/jquery.validate-init.js',
    '/plugins/tables/js/jquery.dataTables.min.js',
    '/plugins/tables/js/datatable/dataTables.bootstrap4.min.js',
    '/plugins/tables/js/datatable-init/datatable-basic.min.js',
    '/js/styleSwitcher.js',
    '/icons/font-awesome/css/font-awesome.min.css',
    '/icons/simple-line-icons/css/simple-line-icons.css',
    '/icons/weather-icons/css/weather-icons.min.css',
    '/icons/cryptocoins/css/cryptocoins.css',
    '/icons/cryptocoins/css/cryptocoins-colors.css',
    '/icons/linea-icons/linea.css',
    '/icons/ionicons/css/ionicons.css',
    '/icons/themify-icons/themify-icons.css',
    '/icons/flag-icon-css/flag-icon.min.css',
    '/icons/material-design-iconic-font/materialdesignicons.min.css',
    '/icons/pe-icon-set-weather/css/pe-icon-set-weather.min.css',
    '/plugins/animate/animate.min.css',
    '/plugins/metismenu/css/metisMenu.min.css',
    '/plugins/bootstrap-select/dist/css/bootstrap-select.min.css',
    '/css/style.css',
    '/css/style.css.map',
    '/plugins/common/common.min.js',
    '/js/custom.min.js',
    '/js/settings.js',
    '/js/gleek.js',
    '/offline',
    '/tabla',
    '/crear',
    '/css/webcam-demo.css',
    '/js/jquery-3.3.1.min.js',
    '/js/webcam-easy.min.js',
];
//ruta para fallback.
var fallback = "/offline"

//representa el evento cuando se esta instalando el services workers.
self.addEventListener('install', function(event) {
    console.log('Instalando el Services Worker');
     // Utilizando las promesas para agregar los elementos definidos
     event.waitUntil(
         caches.open(CACHE_NAME) //Utilizando el api Cache definido en la variable.
             .then(function(cache) {
                 console.log('Cache abierto');
                 return cache.addAll(urlsToCache); //agregando todos los elementos del cache.
             })
     );
});

/**
 * Los Service Workers se actualizan pero no se activan hasta que la quede una site activo
 * que utilice la versión anterior. Para eliminar el problema, una vez activado borramos los cache no utilizado.
 */
self.addEventListener('activate', evt => {
    console.log('Activando el services worker -  Limpiando el cache no utilizado');
    evt.waitUntil(
        caches.keys().then(function(keyList) { //Recupera todos los cache activos.
            return Promise.all(keyList.map(function(key) {
                if (CACHE_NAME.indexOf(key) === -1) { //si no es el cache por defecto borramos los elementos.
                    return caches.delete(key); //borramos los elementos guardados.
                }
            }));
        })
    );
});

/**
 * Representa el evento que se dispara cuando realizamos una solicitud desde la pagina al servidor.
 * Interceptamos el mensaje y podemos verificar si lo tenemos en el cache o no.
 */
self.addEventListener('fetch', event => {
    event.respondWith(
        caches.match(event.request).then(response=>{
            console.log(event);
            //si existe retornamos la petición desde el cache, de lo contrario (retorna undefined) dejamos pasar la solicitud al servidor,
            // lo hacemos con la función fetch pasando un objeto de petición.
            return response || fetch(event.request);
        }).catch(function (){ //En caso de tener un problema con la red, se mostrará el caso
            return caches.match(fallback);
        })
    );
});
