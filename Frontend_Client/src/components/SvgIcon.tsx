interface SvgProps extends React.SVGProps<SVGSVGElement>{
    name:string,
    prefix:string,
    color:string,
}
/* {name,prefix,color,...props}:{name:string,prefix:string,color:string} & React.SVGProps<SVGSVGElement>  */
export default ({name,prefix,color,...props}:SvgProps) => {
    const symbolId = `#${prefix}-${name}`;
    return (
        <svg {...props} aria-hidden='true'>
            <use href={symbolId} fill={color} />
        </svg>
    );
}





