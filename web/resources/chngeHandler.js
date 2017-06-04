/**
 * Created by timur on 03.06.2017.
 */
function handleChange(input) {
    if (input.value < 0) input.value = 0;
    if (input.value > 100) input.value = 100;
}

$("#regions").change(function(){
    if($("option:selected:last",this).val() == 99){
        $('#regions option').prop('selected', true);
    } else{
        $('#regions option').prop('selected', false);
    }
});

