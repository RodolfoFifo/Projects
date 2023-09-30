const webcamElement = document.getElementById('webcam');

const canvasElement = document.getElementById('canvas');

const snapSoundElement = document.getElementById('snapSound');

const webcam = new Webcam(webcamElement, 'user', canvasElement, snapSoundElement);

let picture;


function iniciarCamara(){
  if (document.getElementById("val-nombre").value.length >= 2 && document.getElementById("val-sector").value.length >= 3 && document.getElementById("val-nivel").value.length >= 5){
    $('.md-modal').addClass('md-show');
    webcam.start()
        .then(result =>{
          cameraStarted();
          //console.log("webcam started");
        })
        .catch(err => {
            displayError();
        });
  }
}

$('#closeError').click(function() {
    webcam.stop();
    cameraStopped();
    $('.md-modal').addClass('md-show');
    webcam.start()
        .then(result =>{
        cameraStarted();
        //console.log("webcam started");
        })
        .catch(err => {
            displayError();
        });
});

$("#take-photo").click(function () {
    beforeTakePhoto();
    picture = webcam.snap();
    afterTakePhoto();
});

$("#resume-camera").click(function () {
    webcam.stream()
        .then(facingMode =>{
            removeCapture();
        });
});

$("#exit-app").click(function () {
    removeCapture();
    console.log(picture.split(',')[0].split(';')[0].split(':')[1]);
    // console.log(picture.split(',')[1])
    reduce_image_file_size(picture, 100, 100).then(function(newImg){
      guardar(newImg.split(',')[0].split(';')[0].split(':')[1], newImg.split(',')[1]);
  });
});

function displayError(err = ''){
    if(err!=''){
        $("#errorMsg").html(err);
    }
    $("#errorMsg").removeClass("d-none");
}

function cameraStarted(){
    $("#errorMsg").addClass("d-none");
    $('.flash').hide();
    $(".webcam-container").removeClass("d-none");
    window.scrollTo(0, 0); 
    $('content-body').css('overflow-y','hidden');
}

function cameraStopped(){
    $("#errorMsg").addClass("d-none");
    $(".webcam-container").addClass("d-none");
    $('.md-modal').removeClass('md-show');
}


function beforeTakePhoto(){
    $('.flash')
        .show() 
        .animate({opacity: 0.3}, 500) 
        .fadeOut(500)
        .css({'opacity': 0.7});
    window.scrollTo(0, 0); 
    $('#cameraControls').addClass('d-none');
}

function afterTakePhoto(){
    webcam.stop();
    $('#canvas').removeClass('d-none');
    $('#take-photo').addClass('d-none');
    $('#exit-app').removeClass('d-none');
    $('#resume-camera').removeClass('d-none');
    $('#cameraControls').removeClass('d-none');
}

function removeCapture(){
    $('#canvas').addClass('d-none');
    $('#cameraControls').removeClass('d-none');
    $('#take-photo').removeClass('d-none');
    $('#exit-app').addClass('d-none');
    $('#resume-camera').addClass('d-none');
}

async function reduce_image_file_size(base64Str, MAX_WIDTH = 450, MAX_HEIGHT = 450) {
  let resized_base64 = await new Promise((resolve) => {
      let img = new Image()
      img.src = base64Str
      img.onload = () => {
          let canvas = document.createElement('canvas')
          let width = img.width
          let height = img.height

          if (width > height) {
              if (width > MAX_WIDTH) {
                  height *= MAX_WIDTH / width
                  width = MAX_WIDTH
              }
          } else {
              if (height > MAX_HEIGHT) {
                  width *= MAX_HEIGHT / height
                  height = MAX_HEIGHT
              }
          }
          canvas.width = width
          canvas.height = height
          let ctx = canvas.getContext('2d')
          ctx.drawImage(img, 0, 0, width, height)
          resolve(canvas.toDataURL()) // this will return base64 image results after resize
      }
  });
  return resized_base64;
}