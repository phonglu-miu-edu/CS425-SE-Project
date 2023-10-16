import "./ActionIcon.scss";

const ActionIcon = props => {
    const {icon, onClick} = props;

    return <div className="action-icon" onClick={onClick}>
        {icon}
    </div>;
};

export default ActionIcon;