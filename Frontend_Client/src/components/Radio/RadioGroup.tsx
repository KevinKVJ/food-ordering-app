import {
    ChangeEvent,
    Children,
    cloneElement,
    FC,
    PropsWithChildren,
    ReactElement,
} from 'react';

// import { ReactElement } from 'react';
import Radio from './Radio';
import { IRadioProps } from './RadioTypes';

interface IRadioGroup extends PropsWithChildren {
    name: string;
    value: string;
    onChange?: (e: ChangeEvent<HTMLInputElement>) => void;
    children:
        | ReactElement<IRadioProps, typeof Radio>
        | ReactElement<IRadioProps, typeof Radio>[];
}

const RadioGroup: FC<IRadioGroup> = ({ children, name, onChange, value }) => {
    const RGChildren = Children.map(children, (child, index) => {
        if (child.type !== Radio) {
            console.warn('Child Type is not Correct!');
            return;
        }
        // console.log(child.props);
        return cloneElement(child, {
            name,
            key: index,
            onChange,
            onClick: onChange,
            checked: child.props.value === value,
        } as Partial<ReactElement<IRadioProps, typeof Radio>>);
    });
    /* {Children.map(children, (Child, index) => {
                if (Child.type !== Radio) {
                    console.warn('Child Type is not Correct!');
                    return;
                }
                Child();
            })} */
    return <div>{RGChildren}</div>;
};
export default RadioGroup;
