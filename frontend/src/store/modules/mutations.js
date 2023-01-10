import axios from 'axios';
import storage from "@/store/modules/storage";

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
            alert(res.data)
            if(res.data == "ok"){
                //storage.fetch(state);
                alert("가입 완료")
            } else {
                alert("등록 실패");
            }
        });
}
// // 아이템 하나 삭제
// const removeOneItem = (state, payload) => {
//     /* 서버 통신 */
//     axios
//         .put('/todos/delete/' + payload.todoItem.id)
//         .then(res => {
//             if(res.data == "ok"){
//                 storage.fetch(state);
//             } else {
//                 alert("삭제 실패");
//             }
//         });
// }

export { signup };