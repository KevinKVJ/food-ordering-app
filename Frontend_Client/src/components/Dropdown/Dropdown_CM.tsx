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
    minHeight?: string | number;
    minWidth?: string | number;
}

const Dropdown: FC<IDropdown> = ({
    activeSwitch,
    minHeight = 300,
    minWidth = 300,
    children,
    onClose = () => null,
}) => {
    const [mountSwitch, setMountSwitch] = useState(false);
    const [dropdownActive, setDropdownActive] = useState(false);
    const [dynamicZIndex, setDynamicZIndex] = useState(0);
    const ddwrapper = useRef<HTMLDivElement>(null);

    useLayoutEffect(() => {
        if (activeSwitch) {
            setMountSwitch(true);
            setDynamicZIndex(1000);
        }
    }, [activeSwitch]);

    useEffect(() => {
        if (activeSwitch) {
            setDropdownActive(true);
        } else {
            dropdownActive && setDropdownActive(false);
        }
    }, [activeSwitch]);

    useEffect(() => {
        // activeSwitch && setTransitionActive(true);
        function assertIsNode(e: EventTarget | null): asserts e is Node {
            if (!e || !('nodeType' in e)) {
                throw new Error(`Node expected`);
            }
        }
        const closeEventFunc = (e: MouseEvent) => {
            assertIsNode(e.target);
            if (!ddwrapper.current?.contains(e.target)) {
                setDropdownActive(false);
            }
        };
        activeSwitch && document.addEventListener('click', closeEventFunc, true);
        return () => {
            document.removeEventListener('click', closeEventFunc);
        };
    }, [activeSwitch]);

    const dropdownWrapperStyle = useMemo(
        () =>
            ({
                '--ddWOpacity': dropdownActive ? 1 : 0,
                '--ddMinWidth': `${minWidth}px`,
                '--ddMinHeight': `${minHeight}px`,
                '--ddWZIndex': dynamicZIndex,
            } as CSSProperties),
        [dropdownActive, dynamicZIndex]
    );

    const handleClose = () => {
        if (!dropdownActive) {
            setMountSwitch(false);
            setDynamicZIndex(0);
            onClose();
        }
    };

    return mountSwitch ? (
        <div
            ref={ddwrapper}
            className={dStyle.dropdownWrapper}
            style={dropdownWrapperStyle}
            onTransitionEnd={handleClose}
        >
            <div className={dStyle.dropdown}>{children}</div>
        </div>
    ) : null;
};

// Dropdown.defaultProps = {
//     children: <div></div>,
// };

export default Dropdown;
