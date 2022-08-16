import {
    CSSProperties,
    FC,
    PropsWithChildren,
    useEffect,
    useLayoutEffect,
    useMemo,
    useRef,
    useState,
} from 'react';

import dStyle from './dropdown.module.scss';

interface IDropdown extends PropsWithChildren {
    activeSwitch: boolean;
    onClose?: () => void;
}

const Dropdown: FC<IDropdown> = ({ activeSwitch, children, onClose = () => null }) => {
    const [dropdownActive, setDropdownActive] = useState(false);
    const [dynamicZIndex, setDynamicZIndex] = useState(0);
    const ddwrapper = useRef<HTMLDivElement>(null);

    useLayoutEffect(() => {
        activeSwitch && setDynamicZIndex(1000);
        setDropdownActive(activeSwitch);
    }, [activeSwitch]);

    const dropdownWrapperStyle = useMemo(
        () =>
            ({
                '--ddWOpacity': dropdownActive ? 1 : 0,
                '--ddWidth': `${300}px`,
                '--ddHeight': `${300}px`,
                '--ddWZIndex': dynamicZIndex,
            } as CSSProperties),
        [dropdownActive, dynamicZIndex]
    );

    useEffect(() => {
        // activeSwitch && setTransitionActive(true);
        function assertIsNode(e: EventTarget | null): asserts e is Node {
            if (!e || !('nodeType' in e)) {
                throw new Error(`Node expected`);
            }
        }
        const closeEventFunc = (e: MouseEvent) => {
            assertIsNode(e.target);
            !ddwrapper.current?.contains(e.target) && onClose();
        };
        activeSwitch && document.addEventListener('click', closeEventFunc);
        return () => {
            document.removeEventListener('click', closeEventFunc);
        };
    }, [activeSwitch]);

    const handleClose = () => {
        if (!dropdownActive) {
            onClose();
            setDynamicZIndex(0);
        }
    };

    return (
        <div
            ref={ddwrapper}
            className={dStyle.dropdownWrapper}
            style={dropdownWrapperStyle}
            onTransitionEnd={handleClose}>
            <div className={dStyle.dropdown}>{children}</div>
        </div>
    );
};

// Dropdown.defaultProps = {
//     children: <div></div>,
// };

export default Dropdown;
