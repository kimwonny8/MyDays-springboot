import axios from 'axios';

const signup = async (state, {id, pw, name, birth}) => {
    /* 서버 통신 */
    var jsonValue = {
        id: id,
        password: pw,
        name: name, 
        birth: birth
    }

    await axios
        .post('/user/save', JSON.stringify(jsonValue))
        .then(res => {
            //alert(res.data)
            if(res.data == "ok"){
                alert("회원가입 완료")
            } else {
                alert("회원가입 실패");
            }
        });
}

const login = async (state, id) => {
    await axios
        .post('/user/login', id)
        .then(res => {
            alert(res.data)
            if(res.data == "ok"){
                alert("조회 성공")
            } else {
                alert("조회 실패");
            }
        })
}

export { signup };