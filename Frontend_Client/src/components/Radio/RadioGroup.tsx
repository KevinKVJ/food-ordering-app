import type { ChangeEvent, FC, PropsWithChildren, ReactElement } from 'react';
import { Children, cloneElement } from 'react';

// import { ReactElement } from 'react';
import Radio from './Radio';

interface IRadioGroup extends PropsWithChildren {
    name: string;
    onChange?: (e: ChangeEvent<HTMLInputElement>) => void;
    children: ReactElement<typeof Radio> | ReactElement<typeof Radio>[];
}

const RadioGroup: FC<IRadioGroup> = ({ children, name, onChange }) => {
    return (
        <div>
            {/* {Children.map(children, (Child, index) => {
                if (Child.type !== Radio) {
                    console.warn('Child Type is not Correct!');
                    return;
                }
                Child();
            })} */}
            {Children.map(children, (child, index) => {
                if (child.type !== Radio) {
                    console.warn('Child Type is not Correct!');
                    return;
                }
                return cloneElement(child, {
                    name,
                    key: index,
                    onChange,
                    onClick: onChange,
                } as Partial<ReactElement<typeof Radio>>);
            })}
        </div>
    );
};
export default RadioGroup;
