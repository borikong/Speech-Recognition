package com.google.cloud.android.speech.DailyTest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by DS on 2017-07-05.
 */

public class DailyTestQuestionDB {

    public static SQLiteDatabase db;
    public static String db_name = "HangyeolDB0822", tb_name = "HangyeolTB0822";
    static int recordNum; //테이블의 행의 수
    static String res_Record="";
    static String records="";


    String record1=    "01.\n" +
            "상투적인 답변은 [지양/지향]하는 것이 좋습니다.\n" +
            "나의 꿈은 앱을 [개발/계발]하는 프로그래머 입니다.\n" +
            "철수는 가게가 [한창/한참] 바쁠 때, 가게를 비웠습니다.\n" +
            "계란 유통업체가 계란 값을 [담합/단합]한다는 제보가 들어왔습니다. ";
    String record2= "02.\n" +
            "[하늘, 상승, 발생, 혼동, 책]을 이용하여 30초 동안 이야기를 해주세요. ";
    String record3=
            "03.\n"+
                    "인공지능시대에 대비하는 우리의 자세에 대해 말해 보시오.";
    String record4=    "04. 다음 문장을 따라 읽으시오.\n" +
            "척추측만증\n" +
            "확률분포표\n"+
            "저 분은 백 법학박사이고 이 분은 박 법학박사이다\n"+
            "들의 콩깍지는 깐 콩깍지인가 안깐 콩깍지인가 깐 콩깍지면 어떻고 안 깐 콩각지면어떠냐 깐 콩까지나 안 깐 콩깍지나 콩깍지는 다 콩깍지인데";
    String record5=
            "05. 다음 지문을 따라 읽으시오.\n" +
                    "옛날 옛적 어느 날 하얀 눈이 내리는 겨울, 공주가 태어났습니다.\n" +
                    "왕과 왕비는 눈처럼 하얗고 예쁜 공주를 보고 백설공주라고 이름을 지어줬습니다.\n" +
                    "백설공주는 왕과 왕비와 함께 하루하루 행복한 나날을 보냈지만 갑작스런 왕비의 죽음은 백설공주의 앞으로의 \n" +
                    "삶에 불행을 예고했습니다. 왕은 엄마가 없는 가엾은 백설공주를 위하여 새 왕비를 들였습니다.\n" +
                    "그러나 왕비는 아름다움에 집착하는 마녀였고 그녀는 매일 자신이 가지고 있는 거울에게 물었습니다.\n" +
                    "\"거울아 거울아 세상에서 누가 가장 예쁘니\"\n" +
                    "\"이 세상에서 가장 아름다운 사람은 백설공주 이십니다\"\n" +
                    "새 왕비는 자신보다 더 예쁜 백설공주에게 시샘을 느꼈고 결국 백설공주를 성에서 멀리 떨어진 숲에 버려두고 옵니다.\n" +
                    "숲에 버려진 백설공주는 갈곳도 없고 배도 고파 하염없이 울었습니다.\n" +
                    "그러다가 한 난쟁이가 백설공주를 발견하였고 백설공주를 자신의 집에 초대합니다.\n" +
                    "그 곳에는 일곱 난쟁이가 있었고 곧 일곱 난쟁이와 백설공주는 행복한 하루하루를 보냅니다.\n" +
                    "그러나 새 왕비는 백설공주가 살아있다는 사실을 알게 되고 백설공주를 죽이려 일곱 난쟁이 집을 찾아갑니다.\n" +
                    "새 왕비는 사과를 파는 할머니로 변장하고 백설공주에게 독이 든 사과를 건냈습니다.\n" +
                    "착하고 순진한 백설공주는 새 왕비가 준 사과를 먹고 숨이 멎었습니다.\n" +
                    "일하다가 돌아온 일곱 난쟁이들은 백설공주의 죽음을 슬퍼하며 그녀를 유리관에 눕혔습니다.\n" +
                    "그러던 어느 날 이웃나라 왕자님이 숲 속 유리관 안에 누워져 있는 백설공주를 발견하고 그녀의 아름다움에\n" +
                    "반해 키스를 했고 백설공주는 왕자님의 키스로 인해 눈을 뜨게 됐습니다.\n" +
                    "난쟁이들은 기뻐했고 백설공주는 왕자님과 결혼해서 행복하게 살았답니다. ";

    String record11=  "01.\n" +
            " 아이들을 [가르키는/가르치는]일은 책임감이 필요합니다.\n" +
            "            옳고 그름을 [가리는/가르는]일은 어렵습니다. \n" +
            "            차 안에 [이따가/있다가] 뛰쳐나간 범인을 잡고자 안간힘을 썼습니다. \n" +
            "            조각과 바닥의 홈을 보고 퍼즐을 [맞히었다/맞추었다].";
    String record12="02.\n" +
            "[홍보, 할인, 평가, 인정, 수상]을 이용하여 30초 동안 이야기를 해주세요.";
    String record13=   "03. 자신이 가장 잘 하는 요리의 조리법에 대해 설명하시오.";
    String record14= "04. 다음 문장을 따라 읽으시오.\n" +
            "강력접착제 \n" +
            "잡곡밥\n" +
            "작년에 온 솥 장수는 새 솥장수이고 금년에 온 솥장수는 헌 솥장수이다\n" +
            "저기 가는 저 상장사가 새 상 상장사냐 헌 상 상장사냐";

    String record15=  "05. 다음 지문을 따라 읽으시오.\n\n" +
            " 살충제 계란 이 검출된 농장 6곳 가운데 5곳이 친환경 인증을 받은 곳으로 확인됐다. " +
            "심지어 기준치의 약 21배를 초과해서 검출된 곳도 친환경 인증을 받은 농가였다." +
            " 대형 마트에서 유통된 계란 두 제품도 기준치를 초과했는데 이들 역시 친환경·무항생제로 팔리던 제품이었다." +
            "작년 말 기준으로 3000마리 이상을 사육하는 산란계 농장(1060곳)의 73%(780곳)가 친환경 인증을 받았다. " +
            "이 농장들에서 생산하는 이른바 친환경 인증 계란이 전체 유통 물량의 80~90%를 차지한다. " +
            "소비자는 믿고 안심할 수 있는 제품을 구입하려고 보통 계란보다 40% 비싸게 구매해 왔는데 속고 산 셈이 됐다.\n" +
            "친환경 인증 제도란 소비자들이 믿고 먹을 수 있는 안전한 농축산물을 생산하도록 도입된 제도다. " +
            "하지만 기준은 제대로 지키지 않은 채 인증만 남발돼 왔다. 축산물 친환경성을 평가하는 민간 인증기관이 39곳이나 된다. " +
            "농식품부가 작년 무항생제 인증을 강화하는 내용의 개정안을 마련했지만 축산 농가들의 반대로 제도 시행을 내년 1월로 미뤘다. " +
            "축산 농가의 돈벌이가 소비자 안전보다 우선한 것이다.\n" +
            "정부는 앞으로 이틀이면 계란 유통이 100% 가까이 정상화될 것이라고 했다. 지금까지 조사 결과로는 문제가 있는 농가의 비율도 극소수다. 살충제 계란이라고 하더라도 인체에 영향을 미칠 정도는 아니라고 한다. " +
            "그런데도 이런 파동이 벌어지는 것은 류영진 식품의약품안전처장이 지난 10일 \"국내 계란은 안심해도 된다\"고 했기 때문이다. " +
            "식품 문제는 정부 신뢰가 무너지면 걷잡을 수 없게 된다..\n" +
            "출처:조선일보 2017년 08월 17일 03시 18분, [사설] 이틀 내 계란 파동 수습 약속만은 지키라\n";

    String record21=   "01\n" +
            "12시에 예정되어 있던 식사를 오후 1시로 [연기/연장]하였다.\n" +
            "다음 밑줄 친 부분 중 나머지 넷과 [틀린/다른] 하나는? \n" +
            "사람이 가득찬 광장에서 넘어지면 매우 [곤혹/곤욕]스럽다. \n" +
            "서울시는 서울시립박물관을 [갱신/경신]하였다.";
    String record22=   "02\n" +
            "[항소, 언론, 상해, 사태, 참석]을 이용하여 30초 동안 이야기를 해주세요.";
    String record23=    "03. 자신이 관심있는 분야에 대하여 설명하시오.";
    String record24=  "04. 다음 문장을 따라 읽으시오.\n" +
            "상담담당선생님 \n" +
            "참치꽁치찜\n" +
            "옆집 팥죽은 붉은 팥죽이고 뒷집 콩죽은 검은 콩죽이다\n" +
            "멍멍이네 꿀꿀이는 멍멍해도 꿀꿀하고 꿀꿀이네 멍멍이는 꿀꿀해도 멍멍하네";
    String record25= "05. 다음 지문을 따라 읽으시오.\n" +
            "1) 불면증\n" +
            "\n" +
            "불면증은 잠들기 힘들거나, 잠은 들지만 자주 깨고, 새벽에 너무 일찍 잠에서 깨어 수면부족 상태가 되어, 이로 인해 낮동 안 피로감, 졸음, 의욕상실 등의 결과를 초래하는 대표적인 수면장애입니다. 불면증의 원인은 매우 다양합니다. 평소 수면리듬이 약한 사람이, 심리적인 스트레스를 겪으면서 수면리듬이 더욱 심하게 약화되고, 그 결과 잘못된 수면습관을 가지게 되면서 불면증이 생기게 됩니다.\n" +
            "\n" +
            "이후 심리적 스트레스 등이 줄어들더라도 잘못된 수면습관이 남아 있어 불면증을 지속시키며, 만성불면증으로 발전하기도 합니다. 우울증, 불안장애 및 기타 정신질환이 있는 경우에도 불면증상은 흔히 나타납니다. 불면증상이 지속되거나, 우울하거나 불안한 기분이 들거나, 그 외의 다른 증상이 있을 경우, 단순 불면증보다는 다른 정신과적 문제에 동반된 불면증일 가능성이 큽니다.\n" +
            "\n" +
            "2) 기면증\n" +
            "\n" +
            "기면증은 야간에 6시간 이상 충분한 수면을 취함에도 낮에 심한 졸음을 호소하는 과다수면증 중 하나입니다. 낮 동안 졸음 외에도 감정적으로 흥분할 때 힘이 빠지는 탈력발작 증상이 나타날 수 있으며, 수면마비(가위눌림), 잠들 무렵 환각 증상 등이 동반될 수도 있습니다. 기면증은 그 증상이 청소년기에 처음 나타나는 경우가 많으며, 심한 졸음으로 학업에 장애를 초래하며, 운전 중인 사람에게는 사고 위험을 높입니다.\n" +
            "\n" +
            "3) 하지불안증후군\n" +
            "\n" +
            "하지불안증후군은 잠들 무렵 다리(특히 종아리 부근)에 느껴지는 말로 표현하기 힘든 불편감으로 잠들기 힘들어, 수면부족을 초래하는 수면장애입니다. 다리에 느껴지는 불편감에 대해 환자들은 ‘전기가 흐르는 느낌’, ‘벌레가 기어가는 느낌’ 등으로 다양하게 표현하고 통증을 호소하지는 않습니다. 불편감은 낮에도 나타날 수 있으며, 대개 야간에 심해지며 다리를 움직이거나 주물러 주면 호전되는 경우가 많습니다. 낮 동안에도 다리를 같은 자세로 오래가만히 있어야 하는 경우에 더 심해집니다.\n" +
            "\n" +
            "하지불안증후군은 50대 이후에 발병하는 경우가 흔하지만, 최근의 보고에서는 7세 이전의 아동에게서도 나타난다고 합니다. 그리고 성장통으로 알고 있는 소아 하지불편감 중 일부는 소아하지불안증후군이라는 보고도 있습니다. 또한 임신 중 철분이 부족한 경우 하지불안증후군이 발병하는 경우도 흔히 있습니다.\n" +
            "\n" +
            "4) 코골이/수면무호흡증\n" +
            "\n" +
            "코골이는 매우 흔한 생리적인 현상입니다. 그러나 코골이가 있는 사람의 75%는 수면 중에 호흡이 멈추는 수면무호흡증을 동반합니다. 수면무호흡증은 주변사람이 관찰해도 알기 힘든 경우가 있습니다. 수면무호흡증이 하룻밤에 40회 이상 나타나는 경우에는 깊은 잠을 자지 못하고, 체내 산소 공급이 어렵게 되어, 낮 동안 피로감, 자도 잔 것 같지 않은 느낌, 아침 두통, 무기력감, 중력과 기억력 저하, 우울감 등을 유발합니다.\n" +
            "\n" +
            "수면무호흡증을 치료하지 않고 장기간 방치하면 당뇨, 심장질환, 뇌혈관질환 등 심각한 부작용이 일어납니다. 따라서 수면 중에 코골이가 있고 낮 동안 피로감 등이 나타나는 경우에는 수면무호흡증도 있는지 확인을 한 뒤에 료를 해야 합니다.\n" +
            "\n" +
            "5) 기타 수면장애\n" +
            "\n" +
            "기타 수면장애는 다음과 같은 것들이 있습니다.\n" +
            "\n" +
            "자신도 모르게 다리를 주기적으로 움직이는 주기성사지운동증이 있습니다.이 경우 수면을 방해받아 낮 동안 졸음, 피로감, 불면증상을 경험할 수 있습니다.\n" +
            "\n" +
            "꿈꾸는 수중에 꿈 내용을 행동으로 나타내는 렘수면행동장애가 노인에게 나타날 수 있는데, 심한 잠꼬대 등으로 다른 사람의 수면을 방해하며 꿈 내용을 행동으로 옮기는 과격한 행동으로 자신이나 다른 사람을 다치게 할 수 있습니다. 소아에게는 수면 중에 갑자기 깨어 심하게 울며 달래기 힘든 야경증과, 수면 중에 일어나 걸어다니는 수면보행증(몽유병) 등이 나타날 수 있습니다.\n" +
            "\n" +
            "소아에게도 코골이와 수면무호흡증이 나타날 수 있는데, 이 장애가 있으며 충분히 자지 못해 낮 동안 산만한 행동을 보일 수 있고, 성장이 지연되며 뇌 발달에 장애를 줄 수 있습니다. 이 경우 야간수면다원검사 등으로 평가한 후 적절한 치료를 해야합니다.\n" +
            "\n" +
            "교대근무로 인하여 잠자고 일어나는 시간이 불규칙하거나 자주 바뀌는 경우에는 불면증, 무기력감 등을 경험할 수 있습니다.\n" +
            "\n" +
            "청소년에게는 너무 늦게 자고 늦게 일어나는 수면위상지연증후군이 나타날 수 있고, 반대로 노인에게는 너무 일찍 자고 새벽에 깨어 잠들기 힘든 수면위상지연증후군이 나타날 수 있습니다.\n" +
            "\n" +
            "[네이버 지식백과] 수면장애 (국가건강정보포털 의학정보)\n" +
            "\n";

    String record31=   "01\n" +
            "도박에 중독된 아들은 결국 살림을 [결단/결딴] 내 버렸다. \n" +
            "첫 출근을 한다는 [설렘/설레임] 때문에 잠을 잘 수 없었다.  \n" +
            "이 문제는 회의에 [부치도록/붙이도록] 합시다. \n" +
            "감사함에 [목이 메인다/목이 멘다].";
    String record32=       "02\n" +
            "[비난, 통제, 유지, 파악, 대화]을 이용하여 30초 동안 이야기를 해주세요.";
    String record33=   "03. 자신의 미래에 대하여 설명해 보시오.";
    String record34=  "04. 다음 문장을 따라 읽으시오.\n" +
            "서울특별시 \n" +
            "중앙청창살\n" +
            "저기 저 뜀틀이 내가 뛸 뜀틀인가 내가 안뛸 뜀틀인가\n" +
            "우리집 옆집 앞집 뒷창살은 흩겹창살이고 우리집 뒷집 앞집 옆창살은 겹흩창살이다";
    String record35= "05. 다음 지문을 따라 읽으시오.\n" +
            "제목:침팬지가 인간의 학습능력을 뛰어넘을 수 없는 이유 " +
            "침팬지는 도구를 사용하거나 복잡한 목소리로 의사소통을 하며 문제를 해결하는 등 동물 중에 가장 영리하다고 알려져 있다. " +
            "하지만 침팬지가 아무리 똑똑해도 인간을 쫓아오긴 어렵다. 인간의 학습능력을 침팬지가 따라오지 못하기 때문이다. " +
            "최근 그 이유가 유전자의 제어 능력에 달려있다는 사실이 밝혀졌다. \n" +
            "아이다 고메스-로블레스 미국 조지워싱턴대(GWU) 박사후연구원이 이끈 연구팀은 인간이 침팬지보다 뇌 발달에 관여하는 유전자의 힘이 약하게 작용하기 때문에 침팬지보다 유연하게 학습하고 환경에 적응할 수 있게 됐다고 ‘미국 국립과학원회보(PNAS)’ 온라인판 16일 자에 발표했다. \n" +
            "인간을 포함한 영장류가 다른 동물보다 지능이 뛰어난 이유는 대뇌 표면을 구성하는 ‘대뇌 피질’ 덕분이다. " +
            "대뇌 피질은 출생 이후에도 꾸준히 성장하고 환경에 반응하면서 조직화 되는 ‘가소성(plasticity)’을 지니고 있다." +
            "이 가소성 때문에 신발끈을 매는 법이나 수학문제를 푸는 법 등을 학습하고 사회적으로 발달할 수 있다. \n" +
            "연구팀은 쌍둥이나 형제자매처럼 유전적으로 비슷한 사람 218명과 침팬지 206마리의 뇌를 자기공명영상(MRI) 장치로 촬영했다. " +
            "그리고 뇌 영상을 통해 뇌의 크기와 고랑(sulcus)의 형태와 위치를 비교 분석했다. " +
            "고랑은 피질 표면의 주름이 작게 접혀들어간 것을 뜻한다. \n" +
            "그 결과, 인간과 침팬지 모두 근친 간의 뇌 크기는 거의 비슷한 것으로 밝혀졌다. " +
            "하지만 고랑의 크기와 위치는 다르게 나타났다. " +
            "인간은 근친 사이라도 고랑의 형태와 위치가 현저하게 달랐지만 침팬지는 형제끼리 고랑의 형태와 위치가 상대적으로 비슷한 편이었다.  \n" +
            "로블레스 박사후연구원은 “이는 침팬지가 유전자가 정해 준 출발선에서 크게 벗어나지 못한다는 뜻으로 침팬지의 뇌 발달과 학습능력이 인간보다 제한돼 있다는 것을 시사한다”고 설명했다. " +
            "또 그는 “인간은 유전자가 뇌 발달을 느슨하게 제어하기 때문에 뇌가 외부 환경에 좀 더 민감하게 반응한다”며 “인간은 환경과 경험, 사회적 상호작용 등이 대뇌 피질의 조직화에 기여할 수 있는 여지, 즉 가소성이 크다”고 말했다. \n" +
            "출처:네이버 과학동아, 동아사이언스 송경은 기자";

    String record41= "01\n" +
            "여러분에게 거는 기대가 [자못/사뭇] 큽니다. \n" +
            "이순신장군은 정말 [대인/대인배]입니다. \n" +
            "일명 소두증 바이러스로도 [불리우는/불리는] 지카 바이러스가 발생했습니다.\n" +
            "운동장 화단에는 [철/계절] 이른 개나리가 만개했습니다. ";
    String record42=  "02\n" +
            "[표현, 감동, 의식, 의미, 눈동자]을 이용하여 30초 동안 이야기를 해주세요.";
    String record43= "03. 인터넷에서 익명성이 필요한 경우와 그렇지 않은 경우에 대하여 말해 보시오.";
    String record44=  "04. 다음 문장을 따라 읽으시오.\n" +
            "칠월칠일 \n" +
            "샹숑가수\n" +
            "중앙청 창살은 쌍창살이, 시청의 창살은 외창살이다\n" +
            "앞 집 팥죽은 붉은 팥 풋팥죽이고 뒷집 콩죽은 햇콩단콩 콩죽 우리집 깨죽은 검은깨 깨죽인데 사람들은 햇콩 단콩 콩죽 깨죽 죽먹기를 싫어하더라";
    String record45=   "05. 다음 지문을 따라 읽으시오.\n" +
            "감기의 정의\n" +
            "감기는 바이러스에 의해 코와 목 부분을 포함한 상부 호흡기계의 감염 증상으로, 사람에게 나타나는 가장 흔한 급성 질환 중 하나이다. " +
            "재채기, 코막힘, 콧물, 인후통, 기침, 미열, 두통 및 근육통과 같은 증상이 나타나지만 대개는 특별한 치료 없이도 저절로 치유된다.\n" +
            "감기의 원인\n" +
            "200여개 이상의 서로 다른 종류의 바이러스가 감기를 일으킨다. 그 중 30~50%가 리노바이러스(Rhinovirus)이고 10~15%가 코로나바이러스(Coronavirus)이다. " +
            "성인은 일년에 2~4회, 소아는 6~10회 정도 감기에 걸린다. 감기 바이러스는 사람의 코나 목을 통해 들어와 감염을 일으킨다. " +
            "감기 바이러스를 가지고 있는 환자의 코와 입에서 나오는 분비물이 재채기나 기침을 통해 외부로 나오게 되면 그 속에 있는 감기 바이러스가 공기 중에 존재하다가 건강한 사람의 입이나 코에 닿아 전파된다.\n" +
            "따라서 감기 환자와 가까이 있거나 사람이 많은 곳에 감기 환자가 있으면 감기 바이러스가 잘 전파된다. 이러한 호흡기 감염 경로 외에 감기 환자의 호흡기 분비물이 묻어있는 수건 등을 만진 후 그 손으로 눈이나 코, 입 등을 비볐을 때에도 감기 바이러스에 감염된다. " +
            "실내에서 생활하는 시간이 많은 가을과 겨울에 감기에 더 잘 걸리며, 겨울이 없는 지역에서는 우기에 감기에 더 잘 걸린다. 독감은 감기와 일부 증상이 비슷할 수 있지만 원인 바이러스가 다르며, 증상이나 합병증, 치료법도 다르다.\n" +
            "감기의 증상\n" +
            "감기 바이러스에 노출된 지 1~3일 후에 증상이 나타난다. " +
            "증상은 감기 바이러스가 상부 호흡기계에 어느 정도 침투했는가에 따라 다양하게 나타난다. " +
            "콧물, 코막힘, 목 부위의 통증, 기침과 근육통이 흔하게 나타나는 증상이다. " +
            "성인에게서 열이 나는 경우는 드물거나 미열에 그치지만, 소아에게서는 발열 증상이 흔하게 나타난다. 결막염이 동반되어 눈물이 날 수도 있다. 환자의 연령, 기존에 앓고 있었던 질환, 면역상태 등에 따라 증상의 정도가 달라질 수 있다. " +
            "감기의 경과 중에 다른 합병증이 없어도 콧물이 진해지고, 누렇거나 푸르게 변하기도 한다.\n" +
            "\n" +
            "출처:[네이버 지식백과] 감기 [common cold] (서울대학교병원 의학정보, 서울대학교병원)\n";

    String record51=  "01\n" +
            "한복은 [예스러운/옛스러운] 느낌이 물씬 느껴진다.  \n" +
            "이 약은 부작용이 [염려/우려]돼 판매가 중지되었다. \n" +
            "다가가다는 바로말해도 [거꾸로/반대로] 말해도 같은 표현이다. \n" +
            "연예인의 성형외과 광고는 청소년의 성형을 [장려/조장]한다.";
    String record52=  "02\n" +
            "[도전, 기록, 제품, 종목, 성적]을 이용하여 30초 동안 이야기를 해주세요.";
    String record53= "03. 고령화 시대에 대비한 새로운 직업에는 어떤것이 있을지 말해 보시오. ";
    String record54= "04. 다음 문장을 따라 읽으시오.\n" +
            "촉촉한초코칩 \n" +
            "법학박사\n" +
            "고려고 교복은 고급교복이고 고려고 교복은 고급원단을 사용했다\n" +
            "내가 그린 구름그림은 새털구름 그린 구름그림이고 네가 그린 구름그림은 깃털구름 그린 구름그림이다.";
    String record55= "05. 다음 지문을 따라 읽으시오.\n" +
            "<인공지능>" +
            "인간의 지능으로 할 수 있는 사고, 학습, 자기 개발 등을 컴퓨터가 할 수 있도록 하는 방법을 연구하는 컴퓨터 공학 및 정보기술의 한 분야로서, 컴퓨터가 인간의 지능적인 행동을 모방할 수 있도록 하는 것을 인공지능이라고 말하고 있다.\n" +
            "\n" +
            "또한 인공지능은 그 자체로 존재하는 것이 아니라, 컴퓨터 과학의 다른 분야와 직간접으로 많은 관련을 맺고 있다. 특히 현대에는 정보기술의 여러 분야에서 인공지능적 요소를 도입하여 그 분야의 문제 풀이에 활용하려는 시도가 매우 활발하게 이루어지고 있다.\n" +
            "\n" +
            "① 자연언어처리 분야에서는 이미 자동번역과 같은 시스템을 실용화하며, 특히 연구가 더 진행되면 사람이 컴퓨터와 대화하며 정보를 교환할 수 있게 되므로 컴퓨터 사용에 혁신적인 변화가 오게 될 것이다.\n" +
            "\n" +
            "② 전문가시스템 분야에서는 컴퓨터가 현재 인간이 하고 있는 여러 가지 전문적인 작업들(의사의 진단, 광물의 매장량 평가, 화합물의 구조 추정, 손해 배상 보험료의 판정 등)을 대신할 수 있도록 하는 것이다. 여러 분야 가운데서도 가장 일찍 발전하였다.\n" +
            "\n" +
            "③ 컴퓨터가 TV 카메라를 통해 잡은 영상을 분석하여 그것이 무엇인지를 알아내거나, 사람의 목소리를 듣고 그것을 문장으로 변환하는 것 등의 일은 매우 복잡하며, 인공지능적인 이론의 도입 없이는 불가능하다. 이러한 영상 및 음성 인식은 문자 인식, 로봇 공학 등에 핵심적인 기술이다.\n" +
            "\n" +
            "④ 이론증명은 수학적인 정리를 이미 알려진 사실로부터 논리적으로 추론하여 증명하는 과정으로서 인공지능의 여러 분야에서 사용되는 필수적인 기술이며, 그 자체로도 많은 가치를 지니고 있다.\n" +
            "\n" +
            "⑤ 신경망은 비교적 근래에 등장한 것으로서 수학적 논리학이 아닌, 인간의 두뇌를 모방하여 수많은 간단한 처리기들의 네트워크로 구성된 신경망 구조를 상정하는 것이다. \n" +
            "\n" +
            "출처:[네이버 지식백과] 인공지능 [artificial intelligence, 人工知能] (두산백과)\n";

    String record61=  "01\n" +
            "‘알파고’는 스스로 학습해 가며 [깨우치는/깨치는] 컴퓨터의 최강 고수이다. \n" +
            "염치 [불고하고/불구하고]부탁드립니다. \n" +
            "철수는 수학 공부에 [맛 들였다/맛 들렸다]. \n" +
            "장갑 한 짝을 잃어버려서 이제 이 장갑은 [쓸데없게/쓸모없게] 되었다. ";
    String record62=   "02\n" +
            "[공감, 시대, 하루, 속도, 반복]을 이용하여 30초 동안 이야기를 해주세요.";
    String record63= "03. 자신이 가지고 있는 안좋은 습관을 해결할 방법에 대해 말해 보시오.";
    String record64=   "04. 다음 문장을 따라 읽으시오.\n" +
            "특허허가가 \n" +
            "쌍철창살\n" +
            "앞뜰에 있는 말뚝이 말맬말뚝이냐 말안맬말뚝이냐\n" +
            "생각이란 생각하면 생각할수록 생각나는것이 생각이므로 생각하지않는 생각이 좋은생각이라 생각한다";
    String record65=      "05. 다음 지문을 따라 읽으시오.\n" +
            "당뇨병의 정의\n" +
            "\n" +
            "당뇨병은 인슐린의 분비량이 부족하거나 정상적인 기능이 이루어지지 않는 등의 대사질환의 일종으로, 혈중 포도당의 농도가 높아지는 고혈당을 특징으로 하며, 고혈당으로 인하여 여러 증상 및 징후를 일으키고 소변에서 포도당을 배출하게 된다.\n" +
            "\n" +
            "당뇨병의 원인\n" +
            "\n" +
            "당뇨병은 제1형과 제2형으로 구분되는데, 제1형 당뇨병은 이전에 소아 당뇨병이라고 불렸었으며, 인슐린을 전혀 생산하지 못하는 것이 원인이 되어 발생하는 질환이다. " +
            "인슐린이 상대적으로 부족한 제2형 당뇨병은 인슐린 저항성을 특징으로 한다. " +
            "제2형 당뇨는 식생활의 서구화에 따른 고열량, 고지방, 고단백의 식단, 운동 부족, 스트레스 등 환경적인 요인이 크게 작용하는 것으로 보이지만, 이 외에 특정 유전자의 결함에 의해서도 당뇨병이 생길 수 있으며, 췌장 수술, 감염, 약제에 의해서도 생길 수 있다.\n" +
            "\n" +
            "당뇨병의 증상\n" +
            "\n" +
            "약한 고혈당에서는 대부분의 환자들이 증상을 느끼지 못하거나 모호해서 당뇨병이라고 생각하기 어렵다. " +
            "혈당이 많이 올라가면 갈증이 나서 물을 많이 마시게 되고, 소변량이 늘어 화장실을 자주 가게 된다. 또한 체중이 빠지게 된다. " +
            "오랜 기간 고혈당 상태가 유지되면 신체에서 여러 합병증이 발생하는데, 대표적인 것이 망막병증(실명할 수 있음), 신기능장애(신기능 저하로 심할 경우 투석이 필요함), 신경병증(저림, 통증)이고, 심혈관계 질환의 위험이 높아지게 된다.\n" +
            "\n" +
            "당뇨병의 진단\n" +
            "\n" +
            "혈액검사로 진단한다. 증상이 없는 경우 8시간 이상 금식 후에 측정한 혈당이 126mg/dL 이상이거나, 경구 당부하 검사 2시간 후 혈당이 200mg/dL 이상인 경우를 당뇨병이라 한다. 물을 많이 마시거나 소변이 많아지고 체중이 감소하는 동시에 식사와 무관하게 측정한 혈당이 200mg/dL이상일 때도 당뇨병으로 진단한다.\n" +
            "\n" +
            "출처:[네이버 지식백과] 당뇨병 [diabetes mellitus] (서울대학교병원 의학정보, 서울대학교병원)\n" +
            "\n";




    public DailyTestQuestionDB(){

        insertRecord(""+1, record1);
        insertRecord(""+2, record2);
        insertRecord(""+3, record3);
        insertRecord(""+4, record4);
        insertRecord(""+5, record5);

        insertRecord("" + 11, record11); //행수 1개생김. (=두번째날 )
        insertRecord("" + 12, record12);
        insertRecord("" + 13, record13);
        insertRecord("" + 14, record14);
        insertRecord("" + 15, record15);

        insertRecord("" + 21, record21); //행수 2개생김. (=세번째날 )
        insertRecord("" + 22, record22);
        insertRecord("" + 23, record23);
        insertRecord("" + 24, record24);
        insertRecord("" + 25, record25);

        insertRecord("" + 31, record31); //행수 3개생김. (=네번째날 )
        insertRecord("" + 32, record32);
        insertRecord("" + 33, record33);
        insertRecord("" + 34, record34);
        insertRecord("" + 35, record35);

        insertRecord("" + 41, record41); //행수 4개생김. (=다섯번째날 )
        insertRecord("" + 42, record42);
        insertRecord("" + 43, record43);
        insertRecord("" + 44, record44);
        insertRecord("" + 45, record45);

        insertRecord("" + 51, record51); //행수 5개생김. (=다섯번째날 )
        insertRecord("" + 52, record52);
        insertRecord("" + 53, record53);
        insertRecord("" + 54, record54);
        insertRecord("" + 55, record55);

        insertRecord("" + 61, record61); //행수 6개생김. (=다섯번째날 )
        insertRecord("" + 62, record62);
        insertRecord("" + 63, record63);
        insertRecord("" + 64, record64);
        insertRecord("" + 65, record65);

    }

    public void insertRecord(String order, String speechRecord) {
        int n_order = Integer.parseInt(order);
        db.execSQL("insert into " + tb_name + "(turns, speechRecord) values ( " + n_order + " , '" + speechRecord + "' );");
    }

    public void searchRecord(String randomNum) {
        String[] where = {randomNum};
        Cursor c2 = db.rawQuery("select speechRecord from " + tb_name + " where turns=?", where);
        for (int i = 0; i < c2.getCount(); i++) {
            c2.moveToNext(); //다음번 레코드가 오게 되고
            res_Record = c2.getString(0); //그 레코드 안에서 1번쨰 해당하는애 =(speechRecord)
        }
    }



}
