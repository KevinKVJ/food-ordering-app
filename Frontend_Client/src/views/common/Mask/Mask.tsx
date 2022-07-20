import { createPortal } from 'react-dom';
const Mask = () => {
    return createPortal(<div></div>, document.body);
};

export default Mask;
