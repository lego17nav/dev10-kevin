const CAPSULE_COUNT = 100;
const bookGuestForm = document.getElementById('book-guest');
const checkOutGuestForm = document.getElementById("checkout-guest");
const capsules = [];

function handleGuestFormSubmit(event) {

    event.preventDefault();

    const guestNameElement = bookGuestForm.querySelector("#guest");
    const bookingCapsuleElement = bookGuestForm.querySelector("#bookingCapsule");

    const guestName = guestNameElement.value;
    const bookingCapsuleString = bookingCapsuleElement.value;

    const roomNumber = parseInt(bookingCapsuleString, 10);
    const roomIndex = roomNumber - 1;

    const capsule = capsules[roomIndex];
    capsule.guestName = guestName;

    renderCapsules();
    guestNameElement.value = "";
    bookingCapsuleElement.value = "";

}

function handleCheckOutFormSubmit(event) {
    event.preventDefault();
    const checkOutCapsuleElement = checkOutGuestForm.querySelector("#checkOutCapsule");

    
    const checkOutCapsuleString = checkOutCapsuleElement.value;

    const roomNumber = parseInt(checkOutCapsuleString, 10);
    const roomIndex = roomNumber - 1;

    const capsule = capsules[roomIndex];
    capsule.guestName = null;

    renderCapsules();
    checkOutCapsuleElement.value = "";
}
    
function renderCapsules() {
    const capsuleContainer = document.getElementById("capsules");
    let html = "";
    for (const capsule of capsules) {
        html += `<div>
            <span id="capsuleLabel${capsule.roomNumber}" class="badge badge-pill badge-success">Capsule #${capsule.roomNumber}</span>
            &nbsp;<span id="guest${capsule.roomNumber}">${capsule.guestName ? capsule.guestName : "Unoccupied"}</span>
        </div>`
    }

    capsuleContainer.innerHTML = html;
}

function init() {

    // initialize our capsule array

    for (let i = 0; i < CAPSULE_COUNT; i ++) {
        capsules[i] = {
            roomNumber: i + 1,
            guestName: null
        };
    }
    renderCapsules();
    bookGuestForm.addEventListener("submit", handleGuestFormSubmit);
    checkOutGuestForm.addEventListener("submit", handleGuestFormSubmit);
}

init();