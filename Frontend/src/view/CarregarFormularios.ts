export const adicionarEventoSeExistir = (elementId: string, evento: string, callback: EventListenerOrEventListenerObject) => {
          const element = document.getElementById(elementId);
          if (element) { element.addEventListener(evento, callback); }
}


